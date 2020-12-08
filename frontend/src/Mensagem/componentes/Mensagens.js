import React from 'react';
import Button from 'react-bootstrap/Button';

import CardExpansivel from '../../componentes/CardExpansivel';
import MensagensDataSource from '../Datasource';

let ContextoMensagemSelecionada = React.createContext( null );
let mudaMensagemSelecionada;

export class ListaMensagens extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			mensagens: MensagensDataSource.getMensagens()
		};
	}

	componentDidMount() {
//		MensagensDataSource.addChangeListener(this.handleChange);
	}

	componentWillUnmount() {
//		MensagensDataSource.removeChangeListener(this.handleChange);
	}

	handleChange() {
		this.setState({
			mensagens: MensagensDataSource.getMensagens()
		});
	}

	render() {
		return (
			<CardExpansivel titulo="Mensagens" corpo={(
						<ul>
							{this.state.mensagens.results.map((mensagem) =>
								<li key={mensagem.id}>
									<Button variant="link" onClick={(e) => mudaMensagemSelecionada(mensagem)}>
										{mensagem.titulo}
									</Button>
									{mensagem.likes}
									<Button variant="link" size="sm" className="far fa-thumbs-up"
											onClick={(e) => MensagensDataSource.like(mensagem)} />
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
			selecionada: null,
		};
	}

	selecionaMensagem(mensagem) {
		this.setState({
			selecionada: mensagem
		});
	}

	componentDidMount() {
		mudaMensagemSelecionada = (mensagem) => this.selecionaMensagem(mensagem);
	}

	render() {
		return (
			<ContextoMensagemSelecionada.Provider value={this.state.selecionada}>
				{this.props.children}
			</ContextoMensagemSelecionada.Provider>
		);
	}
}

export { ContextoMensagemSelecionada };