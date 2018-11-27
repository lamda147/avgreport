import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ouunits.reducer';
import { IOuunits } from 'app/shared/model/ouunits.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOuunitsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IOuunitsUpdateState {
  isNew: boolean;
}

export class OuunitsUpdate extends React.Component<IOuunitsUpdateProps, IOuunitsUpdateState> {
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
    values.ouunitDateCreated = new Date(values.ouunitDateCreated);
    values.ouunitLastUpdated = new Date(values.ouunitLastUpdated);

    if (errors.length === 0) {
      const { ouunitsEntity } = this.props;
      const entity = {
        ...ouunitsEntity,
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
    this.props.history.push('/entity/ouunits');
  };

  render() {
    const { ouunitsEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="avgReportApp.ouunits.home.createOrEditLabel">
              <Translate contentKey="avgReportApp.ouunits.home.createOrEditLabel">Create or edit a Ouunits</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : ouunitsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ouunits-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="ouunitidLabel" for="ouunitid">
                    <Translate contentKey="avgReportApp.ouunits.ouunitid">Ouunitid</Translate>
                  </Label>
                  <AvField id="ouunits-ouunitid" type="text" name="ouunitid" />
                </AvGroup>
                <AvGroup>
                  <Label id="ouunitnameLabel" for="ouunitname">
                    <Translate contentKey="avgReportApp.ouunits.ouunitname">Ouunitname</Translate>
                  </Label>
                  <AvField id="ouunits-ouunitname" type="text" name="ouunitname" />
                </AvGroup>
                <AvGroup>
                  <Label id="parentouunitLabel" for="parentouunit">
                    <Translate contentKey="avgReportApp.ouunits.parentouunit">Parentouunit</Translate>
                  </Label>
                  <AvField id="ouunits-parentouunit" type="text" name="parentouunit" />
                </AvGroup>
                <AvGroup>
                  <Label id="grandparentouunitLabel" for="grandparentouunit">
                    <Translate contentKey="avgReportApp.ouunits.grandparentouunit">Grandparentouunit</Translate>
                  </Label>
                  <AvField id="ouunits-grandparentouunit" type="text" name="grandparentouunit" />
                </AvGroup>
                <AvGroup>
                  <Label id="ouunituserfield1Label" for="ouunituserfield1">
                    <Translate contentKey="avgReportApp.ouunits.ouunituserfield1">Ouunituserfield 1</Translate>
                  </Label>
                  <AvField id="ouunits-ouunituserfield1" type="text" name="ouunituserfield1" />
                </AvGroup>
                <AvGroup>
                  <Label id="ouunituserfield2Label" for="ouunituserfield2">
                    <Translate contentKey="avgReportApp.ouunits.ouunituserfield2">Ouunituserfield 2</Translate>
                  </Label>
                  <AvField id="ouunits-ouunituserfield2" type="text" name="ouunituserfield2" />
                </AvGroup>
                <AvGroup>
                  <Label id="ouunituserfield3Label" for="ouunituserfield3">
                    <Translate contentKey="avgReportApp.ouunits.ouunituserfield3">Ouunituserfield 3</Translate>
                  </Label>
                  <AvField id="ouunits-ouunituserfield3" type="text" name="ouunituserfield3" />
                </AvGroup>
                <AvGroup>
                  <Label id="ouunitDateCreatedLabel" for="ouunitDateCreated">
                    <Translate contentKey="avgReportApp.ouunits.ouunitDateCreated">Ouunit Date Created</Translate>
                  </Label>
                  <AvInput
                    id="ouunits-ouunitDateCreated"
                    type="datetime-local"
                    className="form-control"
                    name="ouunitDateCreated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.ouunitsEntity.ouunitDateCreated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ouunitLastUpdatedLabel" for="ouunitLastUpdated">
                    <Translate contentKey="avgReportApp.ouunits.ouunitLastUpdated">Ouunit Last Updated</Translate>
                  </Label>
                  <AvInput
                    id="ouunits-ouunitLastUpdated"
                    type="datetime-local"
                    className="form-control"
                    name="ouunitLastUpdated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.ouunitsEntity.ouunitLastUpdated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="ouunits-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="avgReportApp.ouunits.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ouunits" replace color="info">
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
  ouunitsEntity: storeState.ouunits.entity,
  loading: storeState.ouunits.loading,
  updating: storeState.ouunits.updating,
  updateSuccess: storeState.ouunits.updateSuccess
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
)(OuunitsUpdate);
