import React from 'react';
import { Card, Accordion } from 'react-bootstrap';
import { right } from './Utils';

export default function CardExpansivel(props) {
	const extensao = props.extensao || null;
	return (
		<Accordion defaultActiveKey="0">
			<Card>
				<Card.Header className="row">
					<Accordion.Toggle eventKey="0" as="div" className="d-inline-block col">
						<div className="d-inline-block align-middle">
							{props.titulo}
						</div>
						{right(
							<span className="my-blockquote align-middle">expansivel</span>
						)}
					</Accordion.Toggle>
					{extensao}
				</Card.Header>
				<Accordion.Collapse eventKey="0">
					<Card.Body>
						{props.corpo}
					</Card.Body>
				</Accordion.Collapse>
			</Card>
		</Accordion>
	);
}