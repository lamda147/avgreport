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
import { getEntities } from './addresses.reducer';
import { IAddresses } from 'app/shared/model/addresses.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IAddressesProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IAddressesState = IPaginationBaseState;

export class Addresses extends React.Component<IAddressesProps, IAddressesState> {
  state: IAddressesState = {
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
    const { addressesList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="addresses-heading">
          <Translate contentKey="avgReportApp.addresses.home.title">Addresses</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="avgReportApp.addresses.home.createLabel">Create new Addresses</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressid')}>
                  <Translate contentKey="avgReportApp.addresses.addressid">Addressid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressarea')}>
                  <Translate contentKey="avgReportApp.addresses.addressarea">Addressarea</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressdistrict')}>
                  <Translate contentKey="avgReportApp.addresses.addressdistrict">Addressdistrict</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addresscity')}>
                  <Translate contentKey="avgReportApp.addresses.addresscity">Addresscity</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressstate')}>
                  <Translate contentKey="avgReportApp.addresses.addressstate">Addressstate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressuserfield1')}>
                  <Translate contentKey="avgReportApp.addresses.addressuserfield1">Addressuserfield 1</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressuserfield2')}>
                  <Translate contentKey="avgReportApp.addresses.addressuserfield2">Addressuserfield 2</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressuserfield3')}>
                  <Translate contentKey="avgReportApp.addresses.addressuserfield3">Addressuserfield 3</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressuserfield4')}>
                  <Translate contentKey="avgReportApp.addresses.addressuserfield4">Addressuserfield 4</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressuserfield5')}>
                  <Translate contentKey="avgReportApp.addresses.addressuserfield5">Addressuserfield 5</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressuserfield6')}>
                  <Translate contentKey="avgReportApp.addresses.addressuserfield6">Addressuserfield 6</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressDateCreated')}>
                  <Translate contentKey="avgReportApp.addresses.addressDateCreated">Address Date Created</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('addressLastUpdated')}>
                  <Translate contentKey="avgReportApp.addresses.addressLastUpdated">Address Last Updated</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="avgReportApp.addresses.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {addressesList.map((addresses, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${addresses.id}`} color="link" size="sm">
                      {addresses.id}
                    </Button>
                  </td>
                  <td>{addresses.addressid}</td>
                  <td>{addresses.addressarea}</td>
                  <td>{addresses.addressdistrict}</td>
                  <td>{addresses.addresscity}</td>
                  <td>{addresses.addressstate}</td>
                  <td>{addresses.addressuserfield1}</td>
                  <td>{addresses.addressuserfield2}</td>
                  <td>{addresses.addressuserfield3}</td>
                  <td>{addresses.addressuserfield4}</td>
                  <td>{addresses.addressuserfield5}</td>
                  <td>{addresses.addressuserfield6}</td>
                  <td>
                    <TextFormat type="date" value={addresses.addressDateCreated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={addresses.addressLastUpdated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{addresses.isDeleted ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${addresses.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${addresses.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${addresses.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ addresses }: IRootState) => ({
  addressesList: addresses.entities,
  totalItems: addresses.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Addresses);
