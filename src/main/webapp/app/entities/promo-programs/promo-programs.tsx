import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './promo-programs.reducer';
import { IPromoPrograms } from 'app/shared/model/promo-programs.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPromoProgramsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class PromoPrograms extends React.Component<IPromoProgramsProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { promoProgramsList, match } = this.props;
    return (
      <div>
        <h2 id="promo-programs-heading">
          <Translate contentKey="avgReportApp.promoPrograms.home.title">Promo Programs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="avgReportApp.promoPrograms.home.createLabel">Create new Promo Programs</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.promoID">Promo ID</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.promoname">Promoname</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.promoUserField1">Promo User Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.promoUserField2">Promo User Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.promoUserField3">Promo User Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.promoDateCreated">Promo Date Created</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.promoLastUpdated">Promo Last Updated</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.promoPrograms.isDeleted">Is Deleted</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {promoProgramsList.map((promoPrograms, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${promoPrograms.id}`} color="link" size="sm">
                      {promoPrograms.id}
                    </Button>
                  </td>
                  <td>{promoPrograms.promoID}</td>
                  <td>{promoPrograms.promoname}</td>
                  <td>{promoPrograms.promoUserField1}</td>
                  <td>{promoPrograms.promoUserField2}</td>
                  <td>{promoPrograms.promoUserField3}</td>
                  <td>
                    <TextFormat type="date" value={promoPrograms.promoDateCreated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={promoPrograms.promoLastUpdated} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{promoPrograms.isDeleted ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${promoPrograms.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${promoPrograms.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${promoPrograms.id}/delete`} color="danger" size="sm">
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
      </div>
    );
  }
}

const mapStateToProps = ({ promoPrograms }: IRootState) => ({
  promoProgramsList: promoPrograms.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PromoPrograms);
