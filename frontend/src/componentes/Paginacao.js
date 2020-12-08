import React from 'react';
import { Pagination } from 'react-bootstrap';

export function Paginacao(props) {
	return (
		<div className="d-inline-block float-right clearfix col-auto" onSubmit={(e) => { this.incluir(); e.preventDefault() }}>
		<div className="d-inline-block float-right clearfix col-auto">
			<Pagination size="sm">
				<Pagination.First />
				<Pagination.Item>{10}</Pagination.Item>
				<Pagination.Prev />
				<Pagination.Item active>{12}</Pagination.Item>
				<Pagination.Next />
				<Pagination.Item>{14}</Pagination.Item>
				<Pagination.Last />
			</Pagination>
		</div>
	);
}