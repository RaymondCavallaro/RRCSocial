import { EventoRequisicao } from './Model';
import Mensagem from './Model';
import { v4 as uuid_v4 } from 'uuid';

//import { api } from '../config.json';

//const QUERY = api + '';

class MensagensDataSourceClass {

	incluir(mensagem) {
		let eventoRequisicao = new EventoRequisicao({tipo: 'incluir', mensagem: mensagem});
		console.log(eventoRequisicao);
	}
	
	like(mensagem) {
		let eventoRequisicao = new EventoRequisicao({tipo: 'like', mensagem: mensagem});
		console.log(eventoRequisicao);
	}
	
	addChangeListener(listener) {
		
	}

	removeChangeListener(listener) {
		
	}
	
	getMensagens() {
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
					conteudo: 'Welcome to learning React!'
				}), new Mensagem({id: '2',
					titulo: 'Installation',
					conteudo: 'You can install React from npm.'
				})
			]
		};
	}
}

const MensagensDataSource = new MensagensDataSourceClass();

export default MensagensDataSource;