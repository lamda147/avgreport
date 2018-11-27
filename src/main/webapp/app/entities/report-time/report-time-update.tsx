import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './report-time.reducer';
import { IReportTime } from 'app/shared/model/report-time.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IReportTimeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IReportTimeUpdateState {
  isNew: boolean;
}

export class ReportTimeUpdate extends React.Component<IReportTimeUpdateProps, IReportTimeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { reportTimeEntity } = this.props;
      const entity = {
        ...reportTimeEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/report-time');
  };

  render() {
    const { reportTimeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="avgReportApp.reportTime.home.createOrEditLabel">
              <Translate contentKey="avgReportApp.reportTime.home.createOrEditLabel">Create or edit a ReportTime</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : reportTimeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="report-time-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="reportTimeIDLabel" for="reportTimeID">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeID">Report Time ID</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeID" type="text" name="reportTimeID" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeHourLabel" for="reportTimeHour">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeHour">Report Time Hour</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeHour" type="string" className="form-control" name="reportTimeHour" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeDayLabel" for="reportTimeDay">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeDay">Report Time Day</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeDay" type="string" className="form-control" name="reportTimeDay" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeYearWeekLabel" for="reportTimeYearWeek">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeYearWeek">Report Time Year Week</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeYearWeek" type="string" className="form-control" name="reportTimeYearWeek" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeMonthLabel" for="reportTimeMonth">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeMonth">Report Time Month</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeMonth" type="string" className="form-control" name="reportTimeMonth" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeYearQuaterLabel" for="reportTimeYearQuater">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeYearQuater">Report Time Year Quater</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeYearQuater" type="string" className="form-control" name="reportTimeYearQuater" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeYearLabel" for="reportTimeYear">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeYear">Report Time Year</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeYear" type="string" className="form-control" name="reportTimeYear" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeUserField1Label" for="reportTimeUserField1">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeUserField1">Report Time User Field 1</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeUserField1" type="text" name="reportTimeUserField1" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeUserField2Label" for="reportTimeUserField2">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeUserField2">Report Time User Field 2</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeUserField2" type="text" name="reportTimeUserField2" />
                </AvGroup>
                <AvGroup>
                  <Label id="reportTimeUserField3Label" for="reportTimeUserField3">
                    <Translate contentKey="avgReportApp.reportTime.reportTimeUserField3">Report Time User Field 3</Translate>
                  </Label>
                  <AvField id="report-time-reportTimeUserField3" type="text" name="reportTimeUserField3" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="report-time-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="avgReportApp.reportTime.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/report-time" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  reportTimeEntity: storeState.reportTime.entity,
  loading: storeState.reportTime.loading,
  updating: storeState.reportTime.updating,
  updateSuccess: storeState.reportTime.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ReportTimeUpdate);
