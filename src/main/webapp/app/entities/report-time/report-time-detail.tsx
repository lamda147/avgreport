import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './report-time.reducer';
import { IReportTime } from 'app/shared/model/report-time.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReportTimeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ReportTimeDetail extends React.Component<IReportTimeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { reportTimeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="avgReportApp.reportTime.detail.title">ReportTime</Translate> [<b>{reportTimeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="reportTimeID">
                <Translate contentKey="avgReportApp.reportTime.reportTimeID">Report Time ID</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeID}</dd>
            <dt>
              <span id="reportTimeHour">
                <Translate contentKey="avgReportApp.reportTime.reportTimeHour">Report Time Hour</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeHour}</dd>
            <dt>
              <span id="reportTimeDay">
                <Translate contentKey="avgReportApp.reportTime.reportTimeDay">Report Time Day</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeDay}</dd>
            <dt>
              <span id="reportTimeYearWeek">
                <Translate contentKey="avgReportApp.reportTime.reportTimeYearWeek">Report Time Year Week</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeYearWeek}</dd>
            <dt>
              <span id="reportTimeMonth">
                <Translate contentKey="avgReportApp.reportTime.reportTimeMonth">Report Time Month</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeMonth}</dd>
            <dt>
              <span id="reportTimeYearQuater">
                <Translate contentKey="avgReportApp.reportTime.reportTimeYearQuater">Report Time Year Quater</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeYearQuater}</dd>
            <dt>
              <span id="reportTimeYear">
                <Translate contentKey="avgReportApp.reportTime.reportTimeYear">Report Time Year</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeYear}</dd>
            <dt>
              <span id="reportTimeUserField1">
                <Translate contentKey="avgReportApp.reportTime.reportTimeUserField1">Report Time User Field 1</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeUserField1}</dd>
            <dt>
              <span id="reportTimeUserField2">
                <Translate contentKey="avgReportApp.reportTime.reportTimeUserField2">Report Time User Field 2</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeUserField2}</dd>
            <dt>
              <span id="reportTimeUserField3">
                <Translate contentKey="avgReportApp.reportTime.reportTimeUserField3">Report Time User Field 3</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.reportTimeUserField3}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="avgReportApp.reportTime.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{reportTimeEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/report-time" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/report-time/${reportTimeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ reportTime }: IRootState) => ({
  reportTimeEntity: reportTime.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ReportTimeDetail);
