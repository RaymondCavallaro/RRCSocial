import React from 'react';
import Button from 'react-bootstrap/Button';
import { Container, Row, Col } from 'react-bootstrap';

import CardExpansivel from '../../componentes/CardExpansivel';
import { right } from '../../componentes/Utils';
import { GerenciadorTarefas, ContextoGerenciadorTarefas } from '../GerenciadorTarefas';
import { Tarefa_status } from '../Model';

export default class Tarefas extends React.Component {
	excluirTarefa(tarefa) {
		GerenciadorTarefas.excluirTarefa(tarefa);
	}

	cancelarTarefa(tarefa) {
		GerenciadorTarefas.cancelarTarefa(tarefa);
	}

	render() {
		return (
			<CardExpansivel titulo="Tarefas" corpo={(
						<Container fluid>
							<Row>
								<Col>
									id
								</Col>
								<Col>
									tipo
								</Col>
								<Col>
									status
								</Col>
								<Col>
									{right(
										'ações'
									)}
								</Col>
							</Row>
							<ContextoGerenciadorTarefas.Consumer>
								{(lock) => GerenciadorTarefas.tarefas.map((tarefa) => (
									<Row key={tarefa.id}>
										<Col>
											{tarefa.id}
										</Col>
										<Col>
											{tarefa.tipo}
										</Col>
										<Col>
											{tarefa.status}
										</Col>
										<Col>
											{right(tarefa.status === Tarefa_status.FINALIZADA ? (
												<Button onClick={() => this.excluirTarefa(tarefa)}>
													<i className="fas fa-times"></i>
												</Button>
											) : tarefa.status === Tarefa_status.PENDENTE ? (
												<Button onClick={() => this.cancelarTarefa(tarefa)}>
													<i className="fas fa-stop"></i>
												</Button>
											) : null)}
										</Col>
									</Row>
								) ) }
							</ContextoGerenciadorTarefas.Consumer>
						</Container>
					)} />
		);
	}
}
