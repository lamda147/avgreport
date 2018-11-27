import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './products.reducer';
import { IProducts } from 'app/shared/model/products.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IProductsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IProductsState = IPaginationBaseState;

export class Products extends React.Component<IProductsProps, IProductsState> {
  state: IProductsState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { productsList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="products-heading">
          <Translate contentKey="avgReportApp.products.home.title">Products</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="avgReportApp.products.home.createLabel">Create new Products</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodID')}>
                  <Translate contentKey="avgReportApp.products.prodID">Prod ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodDesc')}>
                  <Translate contentKey="avgReportApp.products.prodDesc">Prod Desc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodCode')}>
                  <Translate contentKey="avgReportApp.products.prodCode">Prod Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subTypeName')}>
                  <Translate contentKey="avgReportApp.products.subTypeName">Sub Type Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodUserField1')}>
                  <Translate contentKey="avgReportApp.products.prodUserField1">Prod User Field 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodUserField2')}>
                  <Translate contentKey="avgReportApp.products.prodUserField2">Prod User Field 2</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodUserField3')}>
                  <Translate contentKey="avgReportApp.products.prodUserField3">Prod User Field 3</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodUserField4')}>
                  <Translate contentKey="avgReportApp.products.prodUserField4">Prod User Field 4</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prodUserField5')}>
                  <Translate contentKey="avgReportApp.products.prodUserField5">Prod User Field 5</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('productDateCreated')}>
                  <Translate contentKey="avgReportApp.products.productDateCreated">Product Date Created</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('productLastUpdated')}>
                  <Translate contentKey="avgReportApp.products.productLastUpdated">Product Last Updated</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="avgReportApp.products.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {productsList.map((products, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${products.id}`} color="link" size="sm">
                      {products.id}
                    </Button>
                  </td>
                  <td>{products.prodID}</td>
                  <td>{products.prodDesc}</td>
                  <td>{products.prodCode}</td>
                  <td>{products.subTypeName}</td>
                  <td>{products.prodUserField1}</td>
                  <td>{products.prodUserField2}</td>
                  <td>{products.prodUserField3}</td>
                  <td>{products.prodUserField4}</td>
                  <td>{products.prodUserField5}</td>
                  <td>
                    <TextFormat type="date" value={products.productDateCreated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={products.productLastUpdated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{products.isDeleted ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${products.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${products.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${products.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ products }: IRootState) => ({
  productsList: products.entities,
  totalItems: products.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Products);
