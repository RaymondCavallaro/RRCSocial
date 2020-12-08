import React from 'react';
import { Navbar, Container, Row, Col } from 'react-bootstrap';
import { Mensagens, ListaMensagens } from './Mensagem/componentes/Mensagens';
import InclusaoMensagem from './Mensagem/componentes/InclusaoMensagem';
import ConteudoMensagem from './Mensagem/componentes/ConteudoMensagem';

import 'bootstrap/dist/css/bootstrap.min.css';

import './App.css';

class App extends React.Component {
	render() {
		return (
			<div className="App">
				<Navbar bg="light" expand="lg">
					<Navbar.Brand href="#home"><i className="fas fa-backward"></i> Home</Navbar.Brand>
					<Navbar.Toggle aria-controls="basic-navbar-nav" />
					<a className="btn btn-primary my-forkme_banner" href="https://github.com/RaymondCavallaro/TesteSocial">
						View on GitHub <i className="ml-2 fab fa-lg fa-github"></i>
					</a>
				</Navbar>
				<Mensagens>
					<Container fluid>
					  <Row>
					    <Col>
							<InclusaoMensagem />
							<ListaMensagens />
							<ConteudoMensagem />
					    </Col>
					  </Row>
					</Container>
				</Mensagens>
			</div>
		);
	}
}

export default App;
