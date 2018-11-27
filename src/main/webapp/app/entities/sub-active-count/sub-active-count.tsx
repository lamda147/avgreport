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
import { getEntities } from './sub-active-count.reducer';
import { ISubActiveCount } from 'app/shared/model/sub-active-count.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISubActiveCountProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISubActiveCountState = IPaginationBaseState;

export class SubActiveCount extends React.Component<ISubActiveCountProps, ISubActiveCountState> {
  state: ISubActiveCountState = {
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
    const { subActiveCountList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sub-active-count-heading">
          <Translate contentKey="avgReportApp.subActiveCount.home.title">Sub Active Counts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="avgReportApp.subActiveCount.home.createLabel">Create new Sub Active Count</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountID')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountID">Sub Active Count ID</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCounts')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCounts">Sub Active Counts</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountUserField1')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField1">Sub Active Count User Field 1</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountUserField2')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField2">Sub Active Count User Field 2</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountUserField3')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField3">Sub Active Count User Field 3</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountUserField4')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField4">Sub Active Count User Field 4</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountUserField5')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField5">Sub Active Count User Field 5</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountDateCreated')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountDateCreated">Sub Active Count Date Created</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('subActiveCountLastUpdated')}>
                  <Translate contentKey="avgReportApp.subActiveCount.subActiveCountLastUpdated">Sub Active Count Last Updated</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="avgReportApp.subActiveCount.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="avgReportApp.subActiveCount.address">Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="avgReportApp.subActiveCount.product">Product</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="avgReportApp.subActiveCount.reporttime">Reporttime</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="avgReportApp.subActiveCount.unit">Unit</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="avgReportApp.subActiveCount.promo">Promo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {subActiveCountList.map((subActiveCount, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${subActiveCount.id}`} color="link" size="sm">
                      {subActiveCount.id}
                    </Button>
                  </td>
                  <td>{subActiveCount.subActiveCountID}</td>
                  <td>{subActiveCount.subActiveCounts}</td>
                  <td>{subActiveCount.subActiveCountUserField1}</td>
                  <td>{subActiveCount.subActiveCountUserField2}</td>
                  <td>{subActiveCount.subActiveCountUserField3}</td>
                  <td>{subActiveCount.subActiveCountUserField4}</td>
                  <td>{subActiveCount.subActiveCountUserField5}</td>
                  <td>
                    <TextFormat type="date" value={subActiveCount.subActiveCountDateCreated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={subActiveCount.subActiveCountLastUpdated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{subActiveCount.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {subActiveCount.address ? <Link to={`addresses/${subActiveCount.address.id}`}>{subActiveCount.address.id}</Link> : ''}
                  </td>
                  <td>
                    {subActiveCount.product ? <Link to={`products/${subActiveCount.product.id}`}>{subActiveCount.product.id}</Link> : ''}
                  </td>
                  <td>
                    {subActiveCount.reporttime ? (
                      <Link to={`report-time/${subActiveCount.reporttime.id}`}>{subActiveCount.reporttime.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{subActiveCount.unit ? <Link to={`ouunits/${subActiveCount.unit.id}`}>{subActiveCount.unit.id}</Link> : ''}</td>
                  <td>
                    {subActiveCount.promo ? <Link to={`promo-programs/${subActiveCount.promo.id}`}>{subActiveCount.promo.id}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${subActiveCount.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${subActiveCount.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${subActiveCount.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ subActiveCount }: IRootState) => ({
  subActiveCountList: subActiveCount.entities,
  totalItems: subActiveCount.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubActiveCount);
