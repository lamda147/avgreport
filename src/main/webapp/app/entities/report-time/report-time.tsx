import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './report-time.reducer';
import { IReportTime } from 'app/shared/model/report-time.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReportTimeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ReportTime extends React.Component<IReportTimeProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { reportTimeList, match } = this.props;
    return (
      <div>
        <h2 id="report-time-heading">
          <Translate contentKey="avgReportApp.reportTime.home.title">Report Times</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="avgReportApp.reportTime.home.createLabel">Create new Report Time</Translate>
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
                  <Translate contentKey="avgReportApp.reportTime.reportTimeID">Report Time ID</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeHour">Report Time Hour</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeDay">Report Time Day</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeYearWeek">Report Time Year Week</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeMonth">Report Time Month</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeYearQuater">Report Time Year Quater</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeYear">Report Time Year</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeUserField1">Report Time User Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeUserField2">Report Time User Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.reportTimeUserField3">Report Time User Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="avgReportApp.reportTime.isDeleted">Is Deleted</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {reportTimeList.map((reportTime, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${reportTime.id}`} color="link" size="sm">
                      {reportTime.id}
                    </Button>
                  </td>
                  <td>{reportTime.reportTimeID}</td>
                  <td>{reportTime.reportTimeHour}</td>
                  <td>{reportTime.reportTimeDay}</td>
                  <td>{reportTime.reportTimeYearWeek}</td>
                  <td>{reportTime.reportTimeMonth}</td>
                  <td>{reportTime.reportTimeYearQuater}</td>
                  <td>{reportTime.reportTimeYear}</td>
                  <td>{reportTime.reportTimeUserField1}</td>
                  <td>{reportTime.reportTimeUserField2}</td>
                  <td>{reportTime.reportTimeUserField3}</td>
                  <td>{reportTime.isDeleted ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${reportTime.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${reportTime.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${reportTime.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ reportTime }: IRootState) => ({
  reportTimeList: reportTime.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ReportTime);
