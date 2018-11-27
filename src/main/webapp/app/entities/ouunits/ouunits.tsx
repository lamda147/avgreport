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
import { getEntities } from './ouunits.reducer';
import { IOuunits } from 'app/shared/model/ouunits.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IOuunitsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IOuunitsState = IPaginationBaseState;

export class Ouunits extends React.Component<IOuunitsProps, IOuunitsState> {
  state: IOuunitsState = {
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
    const { ouunitsList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="ouunits-heading">
          <Translate contentKey="avgReportApp.ouunits.home.title">Ouunits</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="avgReportApp.ouunits.home.createLabel">Create new Ouunits</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ouunitid')}>
                  <Translate contentKey="avgReportApp.ouunits.ouunitid">Ouunitid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ouunitname')}>
                  <Translate contentKey="avgReportApp.ouunits.ouunitname">Ouunitname</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('parentouunit')}>
                  <Translate contentKey="avgReportApp.ouunits.parentouunit">Parentouunit</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('grandparentouunit')}>
                  <Translate contentKey="avgReportApp.ouunits.grandparentouunit">Grandparentouunit</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ouunituserfield1')}>
                  <Translate contentKey="avgReportApp.ouunits.ouunituserfield1">Ouunituserfield 1</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ouunituserfield2')}>
                  <Translate contentKey="avgReportApp.ouunits.ouunituserfield2">Ouunituserfield 2</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ouunituserfield3')}>
                  <Translate contentKey="avgReportApp.ouunits.ouunituserfield3">Ouunituserfield 3</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ouunitDateCreated')}>
                  <Translate contentKey="avgReportApp.ouunits.ouunitDateCreated">Ouunit Date Created</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ouunitLastUpdated')}>
                  <Translate contentKey="avgReportApp.ouunits.ouunitLastUpdated">Ouunit Last Updated</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="avgReportApp.ouunits.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ouunitsList.map((ouunits, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ouunits.id}`} color="link" size="sm">
                      {ouunits.id}
                    </Button>
                  </td>
                  <td>{ouunits.ouunitid}</td>
                  <td>{ouunits.ouunitname}</td>
                  <td>{ouunits.parentouunit}</td>
                  <td>{ouunits.grandparentouunit}</td>
                  <td>{ouunits.ouunituserfield1}</td>
                  <td>{ouunits.ouunituserfield2}</td>
                  <td>{ouunits.ouunituserfield3}</td>
                  <td>
                    <TextFormat type="date" value={ouunits.ouunitDateCreated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={ouunits.ouunitLastUpdated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{ouunits.isDeleted ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ouunits.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ouunits.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ouunits.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ ouunits }: IRootState) => ({
  ouunitsList: ouunits.entities,
  totalItems: ouunits.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Ouunits);
