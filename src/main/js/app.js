'use strict';

const axios = require('axios').default;
const React = require('react'); 
const ReactDOM = require('react-dom'); 
const root = "/api";

class App extends React.Component { 
	constructor(props) {
		super(props);
		this.state = { attributes: [], links: {}, skus: [], pageSize: 10 };
		this.onCreate = this.onCreate.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
	}

	/* Load first page. */
	loadFromServer(pageSize) {
		axios.get(root + "/skus?page=1&size=" + pageSize).then(res1 => {
			axios.get(res1.data._links.profile.href).then(res2 => {
				this.setState({
					skus: res1.data._embedded.skus,
					attributes: res2.data.alps.descriptor[0].descriptor.map(o => o.name),
					pageSize: pageSize,
					links: res1.data._links
				});
			})
		})
	}

	onNavigate(navUri) {
		axios.get(navUri).then(res => {
			this.setState({
				skus: res.data._embedded.skus,
				attributes: this.state.attributes,
				pageSize: this.state.pageSize,
				links: res.data._links
			});
		});
	}

	onCreate(newSku) {
		axios.post("/api/skus", newSku).then(_res => {
			axios.get("/api/skus").then(res => {
				this.setState({
					...this.state,
					links: res.data._links
				});

				this.onNavigate(this.state.links.last.href);
			});
		});
	}

	onDelete(sku) {
		axios.delete(sku._links.self.href).then(_res => {
			this.loadFromServer(this.state.pageSize);
		})
	}

	componentDidMount() { 
		this.loadFromServer(this.state.pageSize)
	}

	render() {
		return (
			<div>
				<CreateSku
					attributes={this.state.attributes}
					key={this.state.attributes.toString()}
					onCreate={this.onCreate}
				/>
				<SkuList
					links={this.state.links}
					skus={this.state.skus}
					onNavigate={this.onNavigate}
					handleDelete={(sku) => this.onDelete(sku)}
				/>
				<div onChange={(ev) => this.loadFromServer(parseInt(ev.target.value))}>
					<input type="radio" value="5" name="gender" /> 5
					<input type="radio" value="10" name="gender" /> 10
					<input type="radio" value="20" name="gender" /> 20
				</div>
			</div>
		)
	}
}

function SkuList(props) {
	const skus = props.skus.map(sku =>
		<Sku key={sku._links.self.href} handleDelete={() => props.handleDelete(sku)} sku={sku} />
	);

	return (
		<div>
			<table>
				<tbody>
					<tr>
						<th>SKU Number</th>
						<th>Putaway Type</th>
						<th>Description</th>
						<th>Delete</th>
					</tr>
					{skus}
				</tbody>
			</table>
			<div>
				{
					['first', 'prev', 'next', 'last']
						.filter(s => props.links[s])
						.map(s => <button
							key={s}
							onClick={() => props.onNavigate(props.links[s].href)}
						>
							{s}
						</button>
						)
				}
			</div>
		</div>
	);
}

function Sku(props) {
	return (
		<tr>
			<td>{props.sku.name}</td>
			<td>{props.sku.putawayType}</td>
			<td>{props.sku.description}</td>
			<td>
				<button
					className="button-link"
					onClick={() => props.handleDelete()}
				>
					delete
				</button>
			</td>
		</tr>
	);
}

const arrayToObject = (array) => Object.fromEntries(array.map(e => [e, '']));

const camelCaseToSpaced = (text) => text.replace(/([A-Z])/g, ' $1').toLowerCase();

function CreateSku(props) {

	const [formState, setState] = React.useState(
		arrayToObject(props.attributes)
	);
	const inputs = props
		.attributes
		.map(attribute =>
			<input
				key={attribute}
				type="text"
				placeholder={camelCaseToSpaced(attribute)}
				className="field"
				value={formState[attribute]}
				onChange={(ev) => setState({...formState, [attribute]: ev.target.value })}
			>
			</input>
		);

	const handleSubmit = (ev) => {
		ev.preventDefault();
		props.onCreate(formState);
		setState(arrayToObject(props.attributes));
	}

	const [collapsed, setCollapsed] = React.useState(false);

	return (
		<div id="create-sku" >
			<div>
				<h2>Create SKU</h2>
				<h5
					className="link"
					onClick={() => setCollapsed(!collapsed)}
				>
					{collapsed ? "expand" : "collapse"}
				</h5>
			</div>
			{collapsed ? <></> :
				<form>
					{inputs}
					<button onClick={handleSubmit}>Create SKU</button>
				</form>
			}
		</div>
	);
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)