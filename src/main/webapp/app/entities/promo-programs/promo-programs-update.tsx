import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './promo-programs.reducer';
import { IPromoPrograms } from 'app/shared/model/promo-programs.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPromoProgramsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPromoProgramsUpdateState {
  isNew: boolean;
}

export class PromoProgramsUpdate extends React.Component<IPromoProgramsUpdateProps, IPromoProgramsUpdateState> {
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
    values.promoDateCreated = new Date(values.promoDateCreated);
    values.promoLastUpdated = new Date(values.promoLastUpdated);

    if (errors.length === 0) {
      const { promoProgramsEntity } = this.props;
      const entity = {
        ...promoProgramsEntity,
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
    this.props.history.push('/entity/promo-programs');
  };

  render() {
    const { promoProgramsEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="avgReportApp.promoPrograms.home.createOrEditLabel">
              <Translate contentKey="avgReportApp.promoPrograms.home.createOrEditLabel">Create or edit a PromoPrograms</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : promoProgramsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="promo-programs-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="promoIDLabel" for="promoID">
                    <Translate contentKey="avgReportApp.promoPrograms.promoID">Promo ID</Translate>
                  </Label>
                  <AvField id="promo-programs-promoID" type="text" name="promoID" />
                </AvGroup>
                <AvGroup>
                  <Label id="promonameLabel" for="promoname">
                    <Translate contentKey="avgReportApp.promoPrograms.promoname">Promoname</Translate>
                  </Label>
                  <AvField id="promo-programs-promoname" type="text" name="promoname" />
                </AvGroup>
                <AvGroup>
                  <Label id="promoUserField1Label" for="promoUserField1">
                    <Translate contentKey="avgReportApp.promoPrograms.promoUserField1">Promo User Field 1</Translate>
                  </Label>
                  <AvField id="promo-programs-promoUserField1" type="text" name="promoUserField1" />
                </AvGroup>
                <AvGroup>
                  <Label id="promoUserField2Label" for="promoUserField2">
                    <Translate contentKey="avgReportApp.promoPrograms.promoUserField2">Promo User Field 2</Translate>
                  </Label>
                  <AvField id="promo-programs-promoUserField2" type="text" name="promoUserField2" />
                </AvGroup>
                <AvGroup>
                  <Label id="promoUserField3Label" for="promoUserField3">
                    <Translate contentKey="avgReportApp.promoPrograms.promoUserField3">Promo User Field 3</Translate>
                  </Label>
                  <AvField id="promo-programs-promoUserField3" type="text" name="promoUserField3" />
                </AvGroup>
                <AvGroup>
                  <Label id="promoDateCreatedLabel" for="promoDateCreated">
                    <Translate contentKey="avgReportApp.promoPrograms.promoDateCreated">Promo Date Created</Translate>
                  </Label>
                  <AvInput
                    id="promo-programs-promoDateCreated"
                    type="datetime-local"
                    className="form-control"
                    name="promoDateCreated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.promoProgramsEntity.promoDateCreated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="promoLastUpdatedLabel" for="promoLastUpdated">
                    <Translate contentKey="avgReportApp.promoPrograms.promoLastUpdated">Promo Last Updated</Translate>
                  </Label>
                  <AvInput
                    id="promo-programs-promoLastUpdated"
                    type="datetime-local"
                    className="form-control"
                    name="promoLastUpdated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.promoProgramsEntity.promoLastUpdated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="promo-programs-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="avgReportApp.promoPrograms.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/promo-programs" replace color="info">
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
  promoProgramsEntity: storeState.promoPrograms.entity,
  loading: storeState.promoPrograms.loading,
  updating: storeState.promoPrograms.updating,
  updateSuccess: storeState.promoPrograms.updateSuccess
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
)(PromoProgramsUpdate);
