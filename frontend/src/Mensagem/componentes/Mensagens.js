import React from 'react';
import { ToggleButton, Button } from 'react-bootstrap';

import CardExpansivel from '../../componentes/CardExpansivel';
import MensagensDataSource from '../Datasource';
import Mensagem from '../Model';

let ContextoMensagemSelecionada = React.createContext( null );
let GerenciadorMensagens = {};

export class ListaMensagens extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			change: {},
			mensagens: MensagensDataSource.getMensagens()
		};
	}

	componentDidMount() {
		MensagensDataSource.addChangeListener(this.handleChange);
	}

	componentWillUnmount() {
		MensagensDataSource.removeChangeListener(this.handleChange);
	}

	handleChange(evt) {
		let mensagens = this.state.mensagens;
		this.atualizaMensagens(mensagens, evt);
		this.setState({
			change: {},
			mensagens: mensagens
		});
	}

	atualizaMensagens(mensagens, evt) {
		let mensagemReferida = mensagens.find(msg => msg.id === evt.mensagem.id);
		if (mensagemReferida.hash !== evt.mensagem.hash) {
			if (mensagemReferida.hash !== evt.mensagem.hash_anterior) {
				let idx = mensagens.findIndex(mensagemReferida);
				mensagens = mensagens.filter(msg => msg.id !== evt.mensagem.id);
				mensagens.push(idx, evt.mensagem);
				mensagemReferida.filaEventosNaoAtualizados.forEach( eventoNaoAtualizado => {
					mensagens = this.atualizaMensagens(mensagens, eventoNaoAtualizado)
				} );
				return mensagens;
			} else {
				mensagemReferida.filaEventosNaoAtualizados.push(evt);
			}
		}
	}

	vota(mensagem) {
		if (!mensagem.voto) {
			if (mensagem.upvoted) {
				MensagensDataSource.removevote(mensagem);
			} else {
				MensagensDataSource.upvote(mensagem);
			}
			GerenciadorMensagens.atualizaMensagem();
		}
	}

	render() {
		return (
			<CardExpansivel titulo="Mensagens" corpo={(
						<ul>
							{this.state.mensagens.results.map((mensagem) =>
								<li key={mensagem.id}>
									<Button variant="link" onClick={(e) => GerenciadorMensagens.mudaMensagemSelecionada(mensagem)}>
										{mensagem.titulo}
									</Button>
									<ContextoMensagemSelecionada.Consumer>
										{ (selecionada) => 
											<ToggleButton type="checkbox" size="sm"
													disabled={mensagem.voto}
													checked={mensagem.upvoted}
													className="fas fa-thumbs-up"
													onClick={() => this.vota(mensagem)} />
										}
									</ContextoMensagemSelecionada.Consumer>
									{mensagem.upvotes}
								</li>
							)}
						</ul>
					)} />
		);
	}
}

export class Mensagens extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			selecionada: null
		};
	}

	atualizaMensagem() {
		let selecionada = this.state.selecionada;
		this.setState({
			selecionada: new Mensagem(selecionada)
		});
	}

	selecionaMensagem(mensagem) {
		this.setState({
			selecionada: mensagem
		});
	}

	componentDidMount() {
		GerenciadorMensagens.mudaMensagemSelecionada = (mensagem) => this.selecionaMensagem(mensagem);
		GerenciadorMensagens.atualizaMensagem = () => this.atualizaMensagem();
	}

	render() {
		return (
			<ContextoMensagemSelecionada.Provider value={this.state.selecionada}>
				{this.props.children}
			</ContextoMensagemSelecionada.Provider>
		);
	}
}

export { ContextoMensagemSelecionada, GerenciadorMensagens };