import React from 'react';
import ToggleButton from 'react-bootstrap/ToggleButton';

import CardExpansivel from '../../componentes/CardExpansivel';
import MensagensDataSource from '../Datasource';
import { ContextoMensagemSelecionada, GerenciadorMensagens } from './Mensagens';
import Mensagem from '../Model';

export default class ConteudoMensagem extends React.Component {
	vota(selecionada) {
		if (!selecionada.voto) {
			if (selecionada.upvoted) {
				MensagensDataSource.removevote(selecionada);
			} else {
				MensagensDataSource.upvote(selecionada);
			}
		}
		GerenciadorMensagens.atualizaMensagem();
	}
	
	render() {
		return (
			<ContextoMensagemSelecionada.Consumer>
				{ (selecionada) => {
					let extensao, corpo;
					if (!selecionada) {
						extensao = corpo = null;
					} else {
						extensao = (
							<div className="align-middle">
								<ToggleButton type="checkbox" size="sm"
										disabled={selecionada.voto}
										checked={selecionada.upvoted}
										className="fas fa-thumbs-up"
										onClick={() => this.vota(selecionada)} />
								{selecionada.upvotes}
							</div>
						);
						corpo = (
							<div className={selecionada.id}>
								<h3>{selecionada.titulo}</h3>
								<p>{selecionada.conteudo}</p>
							</div>
						);
					}
					return <CardExpansivel titulo="Mensagem Selecionada" extensao={extensao} corpo={corpo} />;
				} }
			</ContextoMensagemSelecionada.Consumer>
		);
	}
}
