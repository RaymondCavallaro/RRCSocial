import React from 'react';

import { Tarefa_status } from './Model';

let ContextoGerenciadorTarefas = React.createContext( null );
let GerenciadorTarefas = {
	tarefas: [],
	ContextoGerenciadorTarefas: ContextoGerenciadorTarefas
};

export class ContextoGerenciadorTarefasProvider extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			change: {}
		};
	}

	adicionaTarefa(tarefas) {
		tarefas.forEach(tarefa => tarefa.status = Tarefa_status.PENDENTE);
		GerenciadorTarefas.tarefas.push(...tarefas);
		this.setState({
			change: {}
		});
	}

	finalizarTarefa(tarefaRealizada) {
		GerenciadorTarefas.tarefas.find(tarefa => tarefa.id === tarefaRealizada.id).status = Tarefa_status.FINALIZADA;
		this.setState({
			change: {}
		})
	}

	excluirTarefa(tarefaParaExclusao) {
		GerenciadorTarefas.tarefas = GerenciadorTarefas.tarefas.filter(tarefa => tarefa.id !== tarefaParaExclusao.id);
		this.setState({
			change: {}
		})
	}

	cancelarTarefa(tarefaParaCancelamento) {
		GerenciadorTarefas.tarefas.find(tarefa => tarefa.id === tarefaParaCancelamento.id).status = Tarefa_status.CANCELADA;
		this.setState({
			change: {}
		});
	}

	componentDidMount() {
		Object.assign(GerenciadorTarefas, {
			adicionaTarefa: (tarefas) => this.adicionaTarefa(tarefas),
			cancelarTarefa: (tarefa) => this.finalizarTarefa(tarefa),
			excluirTarefa: (tarefa) => this.excluirTarefa(tarefa),
			finalizarTarefa: (tarefaRealizada) => this.finalizarTarefa(tarefaRealizada)
		});
	}

	render() {
		return (
			<ContextoGerenciadorTarefas.Provider value={this.state.change}>
				{this.props.children}
			</ContextoGerenciadorTarefas.Provider>
		);
	}

}

export { GerenciadorTarefas, ContextoGerenciadorTarefas };