import React from 'react';
import { Form, Button } from 'react-bootstrap';
import { v4 as uuid_v4 } from 'uuid';

import CardExpansivel from '../../componentes/CardExpansivel';
import MensagensDataSource from '../Datasource';
import Mensagem from '../Model';

export default class InclusaoMensagem extends React.Component {
	constructor(props) {
		super(props);
		let state = {
			titulo: '',
			conteudo: ''
		};
		Object.assign(state, this.validacaoCS(state));
		this.state = state;
	}
	
	incluir() {
		let validacao = this.validacaoCS(this.state);
		if (!(validacao.dica_titulo.length || validacao.dica_conteudo.length)) {
			let resposta = MensagensDataSource.incluir(new Mensagem({
				titulo: this.state.titulo,
				conteudo: this.state.conteudo,
			}));
			resposta && resposta.mensagens.length && (validacao = this.validacaoSS(resposta.mensagens));
		}
		this.setState(validacao);
	}

	validacaoSS(mensagens) {
		let dica_titulo = [];
		let dica_conteudo = [];
		mensagens.forEach(msg => {
			msg.campo === 'titulo' && dica_titulo.push(msg.texto);
			msg.campo === 'conteudo' && dica_conteudo.push(msg.texto);
		});
		return {
			dica_titulo: dica_titulo,
			dica_conteudo: dica_conteudo
		};
	}

	validacaoCS(state) {
		let dica_titulo = [];
		let dica_conteudo = [];

		if (!state.titulo) {
			dica_titulo.push({id: uuid_v4(), texto: 'Não deve ser vazio'});
		}
		if (!state.conteudo) {
			dica_conteudo.push({id: uuid_v4(), texto: 'Não deve ser vazio'});
		}
		return {
			dica_titulo: dica_titulo,
			dica_conteudo: dica_conteudo
		};
	}

	limpar() {
		let state = {
			titulo: '',
			conteudo: ''
		};
		Object.assign(state, this.validacaoCS(state));
		this.setState(state);
	}

	render() {
		return (
			<CardExpansivel titulo="Incluir Mensagem"
					extensao={(
						<div className="d-inline-block float-right clearfix col-auto">
							<Button className="d-inline-block mr-1" onClick={() => this.limpar()}>
								<i className="fas fa-trash"></i> Limpar
							</Button>
							<Form noValidate className="d-inline-block" onSubmit={(e) => { this.incluir(); e.preventDefault() }}>
								<Button type="submit">
									<i className="fas fa-plus"></i> Incluir
								</Button>
							</Form>
						</div>
					)} corpo={(
						<div>
							<Form.Group controlId="inclusao.titulo">
								<Form.Label>Título</Form.Label>
								<Form.Control type="text"
										value={this.state.titulo}
										isInvalid={!!this.state.dica_titulo.length}
										feedback={this.state.dica_titulo}
										onChange={(e) => this.setState({
											titulo: e.target.value
										})}  />
								<Form.Control.Feedback type="invalid">
									<ul>
										{this.state.dica_titulo.map((dica) =>
											<li key={dica.id}>
												{dica.texto}
											</li>
										)}
									</ul>
								</Form.Control.Feedback>
							</Form.Group>
							<Form.Group controlId="inclusao.conteudo">
								<Form.Label>Conteúdo</Form.Label>
								<Form.Control as="textarea" rows={3}
										value={this.state.conteudo}
										isInvalid={this.state.dica_conteudo.length}
										feedback={this.state.dica_conteudo}
										onChange={(e) => this.setState({
											conteudo: e.target.value
										})}  />
								<Form.Control.Feedback type="invalid">
									<ul>
										{this.state.dica_conteudo.map((dica) =>
											<li key={dica.id}>
												{dica.texto}
											</li>
										)}
									</ul>
								</Form.Control.Feedback>
							</Form.Group>							
						</div>
					)} />
		);
	}
}