import React from 'react';
import Button from 'react-bootstrap/Button';

import CardExpansivel from '../../componentes/CardExpansivel';
import MensagensDataSource from '../Datasource';
import { ContextoMensagemSelecionada } from './Mensagens';

export default function ConteudoMensagem(props) {
	return (
		<ContextoMensagemSelecionada.Consumer>
			{ (selecionada) => {
				let extensao, corpo;
				if (!selecionada) {
					extensao = corpo = null;
				} else {
					extensao = (
						<div className="align-middle">
							{selecionada.likes}
							<Button variant="link" size="sm" className="far fa-thumbs-up"
									onClick={(e) => MensagensDataSource.like(selecionada)} />
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