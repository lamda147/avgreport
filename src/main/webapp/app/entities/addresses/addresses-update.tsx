import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './addresses.reducer';
import { IAddresses } from 'app/shared/model/addresses.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAddressesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAddressesUpdateState {
  isNew: boolean;
}

export class AddressesUpdate extends React.Component<IAddressesUpdateProps, IAddressesUpdateState> {
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
    values.addressDateCreated = new Date(values.addressDateCreated);
    values.addressLastUpdated = new Date(values.addressLastUpdated);

    if (errors.length === 0) {
      const { addressesEntity } = this.props;
      const entity = {
        ...addressesEntity,
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
    this.props.history.push('/entity/addresses');
  };

  render() {
    const { addressesEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="avgReportApp.addresses.home.createOrEditLabel">
              <Translate contentKey="avgReportApp.addresses.home.createOrEditLabel">Create or edit a Addresses</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : addressesEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="addresses-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="addressidLabel" for="addressid">
                    <Translate contentKey="avgReportApp.addresses.addressid">Addressid</Translate>
                  </Label>
                  <AvField id="addresses-addressid" type="text" name="addressid" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressareaLabel" for="addressarea">
                    <Translate contentKey="avgReportApp.addresses.addressarea">Addressarea</Translate>
                  </Label>
                  <AvField id="addresses-addressarea" type="text" name="addressarea" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressdistrictLabel" for="addressdistrict">
                    <Translate contentKey="avgReportApp.addresses.addressdistrict">Addressdistrict</Translate>
                  </Label>
                  <AvField id="addresses-addressdistrict" type="text" name="addressdistrict" />
                </AvGroup>
                <AvGroup>
                  <Label id="addresscityLabel" for="addresscity">
                    <Translate contentKey="avgReportApp.addresses.addresscity">Addresscity</Translate>
                  </Label>
                  <AvField id="addresses-addresscity" type="text" name="addresscity" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressstateLabel" for="addressstate">
                    <Translate contentKey="avgReportApp.addresses.addressstate">Addressstate</Translate>
                  </Label>
                  <AvField id="addresses-addressstate" type="text" name="addressstate" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressuserfield1Label" for="addressuserfield1">
                    <Translate contentKey="avgReportApp.addresses.addressuserfield1">Addressuserfield 1</Translate>
                  </Label>
                  <AvField id="addresses-addressuserfield1" type="text" name="addressuserfield1" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressuserfield2Label" for="addressuserfield2">
                    <Translate contentKey="avgReportApp.addresses.addressuserfield2">Addressuserfield 2</Translate>
                  </Label>
                  <AvField id="addresses-addressuserfield2" type="text" name="addressuserfield2" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressuserfield3Label" for="addressuserfield3">
                    <Translate contentKey="avgReportApp.addresses.addressuserfield3">Addressuserfield 3</Translate>
                  </Label>
                  <AvField id="addresses-addressuserfield3" type="text" name="addressuserfield3" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressuserfield4Label" for="addressuserfield4">
                    <Translate contentKey="avgReportApp.addresses.addressuserfield4">Addressuserfield 4</Translate>
                  </Label>
                  <AvField id="addresses-addressuserfield4" type="text" name="addressuserfield4" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressuserfield5Label" for="addressuserfield5">
                    <Translate contentKey="avgReportApp.addresses.addressuserfield5">Addressuserfield 5</Translate>
                  </Label>
                  <AvField id="addresses-addressuserfield5" type="text" name="addressuserfield5" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressuserfield6Label" for="addressuserfield6">
                    <Translate contentKey="avgReportApp.addresses.addressuserfield6">Addressuserfield 6</Translate>
                  </Label>
                  <AvField id="addresses-addressuserfield6" type="text" name="addressuserfield6" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressDateCreatedLabel" for="addressDateCreated">
                    <Translate contentKey="avgReportApp.addresses.addressDateCreated">Address Date Created</Translate>
                  </Label>
                  <AvInput
                    id="addresses-addressDateCreated"
                    type="datetime-local"
                    className="form-control"
                    name="addressDateCreated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.addressesEntity.addressDateCreated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLastUpdatedLabel" for="addressLastUpdated">
                    <Translate contentKey="avgReportApp.addresses.addressLastUpdated">Address Last Updated</Translate>
                  </Label>
                  <AvInput
                    id="addresses-addressLastUpdated"
                    type="datetime-local"
                    className="form-control"
                    name="addressLastUpdated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.addressesEntity.addressLastUpdated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="addresses-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="avgReportApp.addresses.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/addresses" replace color="info">
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
  addressesEntity: storeState.addresses.entity,
  loading: storeState.addresses.loading,
  updating: storeState.addresses.updating,
  updateSuccess: storeState.addresses.updateSuccess
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
)(AddressesUpdate);
