import { v4 as uuid_v4 } from 'uuid';
import hash from 'object-hash';
import openSocket from 'socket.io-client';

import { mensagem } from '../config.json';
import { GerenciadorTarefas } from './GerenciadorTarefas';
import { EventoRequisicao } from './Model';
import Mensagem from './Model';

//const QUERY = mensagem.rest-api + '';
const INSTANCE_ID = 'ist_' + uuid_v4();

class MensagensDataSourceClass {
	constructor() {
		this.listenersEventos = [ GerenciadorTarefas.finalizarTarefa ];
		this.socket = openSocket(mensagem.socket);
		this.socket.on( 'connect', () => {
			GerenciadorTarefas.tarefas.map(tarefa => tarefa.status === 'pendente')
		} );
		this.socket.on( 'eventos', evento => this.listenersEventos.forEach(ltn => ltn(evento)) );
		this.socket.emit('subscribeEventos');
	}

	acknolowge() {
		this.enviaEventos([new EventoRequisicao({
			tipo: 'acknolowge',
			eventos: GerenciadorTarefas.tarefas
		})]);
//		let resposta = {
//			mudancas: {
//				mensagens: [
//					{id: '1', hash: '1'}
//				]
//			},
//			reconhecidos: []
//		};
//		GerenciadorTarefas.tarefas = GerenciadorTarefas.tarefas.filter(
//				evt => !resposta.reconhecidos.contains(evt));
	}

	incluir(mensagem) {
		this.enviaRequisicoesMensagem([new EventoRequisicao({
			tipo: 'incluir',
			mensagem: mensagem
		})]);
	}

	upvote(mensagem) {
		mensagem.voto = true;
		this.enviaRequisicoesMensagem([new EventoRequisicao({
			instancia: INSTANCE_ID,
			tipo: 'upvote',
			mensagem: mensagem
		})]);
	}

	removevote(mensagem) {
		mensagem.voto = true;
		this.enviaRequisicoesMensagem([new EventoRequisicao({
			instancia: INSTANCE_ID,
			tipo: 'removevote',
			mensagem: mensagem
		})]);
	}

	addChangeListener(listener) {
		this.listenersEventos.push(listener);
	}
	
	removeChangeListener(listener) {
		this.listenersEventos.filter(ltn => ltn === listener);
	}

	enviaRequisicoesMensagem(requisicoesMensagem) {
		requisicoesMensagem.forEach(requisicaoMensagem => {
			Object.assign(requisicaoMensagem.mensagem, {
				ultimo_hash: requisicaoMensagem.mensagem.hash,
				hash: hash(requisicaoMensagem.mensagem.hash, requisicaoMensagem.id)
			});
		});
		GerenciadorTarefas.adicionaTarefa(requisicoesMensagem);
		this.enviaEventos(requisicoesMensagem);
	}

	enviaEventos(eventos) {
		eventos.forEach( evento => Object.assign(evento, {
			instancia: INSTANCE_ID
		}) );
		if (this.socket.connected) {
			eventos.forEach( evento => Object.assign(evento, {
				status: 'enviado'
			}) );
			// envia de fato
		}
	}

	getMensagens() {
		// enviar INSTANCE_ID, vai dizer se já votou ou não
		
//		fetch(QUERY)
//		.then(response => {
//			if (response.ok) {
//				return response.json();
//			} else {
//				throw new Error('Something went wrong ...');
//			}
//		})
//		.then(data => this.setState({ hits: data.hits, isLoading: false }))
//		.catch(error => this.setState({ error, isLoading: false }));
		return {
		    "count": 36, 
		    "next": "", 
		    "previous": null, 
		    "results": [
				new Mensagem({id: '1',
					titulo: 'Hello World',
					conteudo: 'Welcome to learning React!',
					upvotes: 1,
					hash: uuid_v4(),
					hash_anterior: '',
					upvoted: true
				}), new Mensagem({id: '2',
					titulo: 'Installation',
					conteudo: 'You can install React from npm.',
					upvotes: 0,
					hash: uuid_v4(),
					hash_anterior: '',
					upvoted: false
				})
			]
		};
	}
}

const MensagensDataSource = new MensagensDataSourceClass();

export default MensagensDataSource;