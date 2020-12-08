import React from 'react';
import { Form, Button } from 'react-bootstrap';
import { v4 as uuid_v4 } from 'uuid';

import CardExpansivel from '../../componentes/CardExpansivel';
import MensagensDataSource from '../Datasource';
import Mensagem from '../Model';

export default class InclusaoMensagem extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			titulo: '',
			dica_titulo: [],
			conteudo: '',
			dica_conteudo: []
		};
		this.validacaoCS();
	}
	
	incluir() {
		console.log(this.state.dica_titulo, this.state.dica_conteudo);
		this.validacaoCS();
		console.log(this.state.dica_titulo, this.state.dica_conteudo);
		MensagensDataSource.incluir(new Mensagem(this.state));
	}

	validacaoCS() {
		let dica_titulo = [];
		let dica_conteudo = [];

		if (!this.state.titulo) {
			dica_titulo.push({id: uuid_v4(), texto: 'Não deve ser vazio'});
		}
		if (!this.state.conteudo) {
			dica_conteudo.push({id: uuid_v4(), texto: 'Não deve ser vazio'});
		}
		this.setState({
			dica_titulo: dica_titulo,
			dica_conteudo: dica_conteudo
		});
	}
	
	render() {
		return (
			<CardExpansivel titulo="Incluir Mensagem"
					extensao={(
						<Form className="d-inline-block float-right clearfix col-auto" onSubmit={(e) => { this.incluir(); e.preventDefault() }}>
							<Button type="submit">
								<i className="fas fa-plus"></i> Incluir
							</Button>
						</Form>
					)} corpo={(
						<div>
							<Form.Group controlId="inclusao.titulo">
								<Form.Label>Título</Form.Label>
								<Form.Control type="text"
										value={this.state.titulo}
										onChange={(e) => this.setState({
											titulo: e.target.value
										})}  />
								<div>
									<ul>
										{this.state.dica_titulo.map((dica) =>
											<li key={dica.id}>
												{dica.texto}
											</li>
										)}
									</ul>
								</div>
							</Form.Group>
							<Form.Group controlId="inclusao.conteudo">
								<Form.Label>Conteúdo</Form.Label>
								<Form.Control as="textarea" rows={3}
										value={this.state.conteudo}
										onChange={(e) => this.setState({
											conteudo: e.target.value
										})}  />
								<div>
									<ul>
										{this.state.dica_conteudo.map((dica) =>
											<li key={dica.id}>
												{dica.texto}
											</li>
										)}
									</ul>
								</div>
							</Form.Group>							
						</div>
					)} />
		);
	}
}