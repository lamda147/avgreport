import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAddresses } from 'app/shared/model/addresses.model';
import { getEntities as getAddresses } from 'app/entities/addresses/addresses.reducer';
import { IProducts } from 'app/shared/model/products.model';
import { getEntities as getProducts } from 'app/entities/products/products.reducer';
import { IReportTime } from 'app/shared/model/report-time.model';
import { getEntities as getReportTimes } from 'app/entities/report-time/report-time.reducer';
import { IOuunits } from 'app/shared/model/ouunits.model';
import { getEntities as getOuunits } from 'app/entities/ouunits/ouunits.reducer';
import { IPromoPrograms } from 'app/shared/model/promo-programs.model';
import { getEntities as getPromoPrograms } from 'app/entities/promo-programs/promo-programs.reducer';
import { getEntity, updateEntity, createEntity, reset } from './sub-active-count.reducer';
import { ISubActiveCount } from 'app/shared/model/sub-active-count.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISubActiveCountUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISubActiveCountUpdateState {
  isNew: boolean;
  addressId: string;
  productId: string;
  reporttimeId: string;
  unitId: string;
  promoId: string;
}

export class SubActiveCountUpdate extends React.Component<ISubActiveCountUpdateProps, ISubActiveCountUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      addressId: '0',
      productId: '0',
      reporttimeId: '0',
      unitId: '0',
      promoId: '0',
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

    this.props.getAddresses();
    this.props.getProducts();
    this.props.getReportTimes();
    this.props.getOuunits();
    this.props.getPromoPrograms();
  }

  saveEntity = (event, errors, values) => {
    values.subActiveCountDateCreated = new Date(values.subActiveCountDateCreated);
    values.subActiveCountLastUpdated = new Date(values.subActiveCountLastUpdated);

    if (errors.length === 0) {
      const { subActiveCountEntity } = this.props;
      const entity = {
        ...subActiveCountEntity,
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
    this.props.history.push('/entity/sub-active-count');
  };

  render() {
    const { subActiveCountEntity, addresses, products, reportTimes, ouunits, promoPrograms, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="avgReportApp.subActiveCount.home.createOrEditLabel">
              <Translate contentKey="avgReportApp.subActiveCount.home.createOrEditLabel">Create or edit a SubActiveCount</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : subActiveCountEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sub-active-count-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="subActiveCountIDLabel" for="subActiveCountID">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountID">Sub Active Count ID</Translate>
                  </Label>
                  <AvField id="sub-active-count-subActiveCountID" type="text" name="subActiveCountID" />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountsLabel" for="subActiveCounts">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCounts">Sub Active Counts</Translate>
                  </Label>
                  <AvField id="sub-active-count-subActiveCounts" type="string" className="form-control" name="subActiveCounts" />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountUserField1Label" for="subActiveCountUserField1">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField1">Sub Active Count User Field 1</Translate>
                  </Label>
                  <AvField id="sub-active-count-subActiveCountUserField1" type="text" name="subActiveCountUserField1" />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountUserField2Label" for="subActiveCountUserField2">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField2">Sub Active Count User Field 2</Translate>
                  </Label>
                  <AvField id="sub-active-count-subActiveCountUserField2" type="text" name="subActiveCountUserField2" />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountUserField3Label" for="subActiveCountUserField3">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField3">Sub Active Count User Field 3</Translate>
                  </Label>
                  <AvField id="sub-active-count-subActiveCountUserField3" type="text" name="subActiveCountUserField3" />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountUserField4Label" for="subActiveCountUserField4">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField4">Sub Active Count User Field 4</Translate>
                  </Label>
                  <AvField id="sub-active-count-subActiveCountUserField4" type="text" name="subActiveCountUserField4" />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountUserField5Label" for="subActiveCountUserField5">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField5">Sub Active Count User Field 5</Translate>
                  </Label>
                  <AvField id="sub-active-count-subActiveCountUserField5" type="text" name="subActiveCountUserField5" />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountDateCreatedLabel" for="subActiveCountDateCreated">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountDateCreated">Sub Active Count Date Created</Translate>
                  </Label>
                  <AvInput
                    id="sub-active-count-subActiveCountDateCreated"
                    type="datetime-local"
                    className="form-control"
                    name="subActiveCountDateCreated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.subActiveCountEntity.subActiveCountDateCreated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="subActiveCountLastUpdatedLabel" for="subActiveCountLastUpdated">
                    <Translate contentKey="avgReportApp.subActiveCount.subActiveCountLastUpdated">Sub Active Count Last Updated</Translate>
                  </Label>
                  <AvInput
                    id="sub-active-count-subActiveCountLastUpdated"
                    type="datetime-local"
                    className="form-control"
                    name="subActiveCountLastUpdated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.subActiveCountEntity.subActiveCountLastUpdated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="sub-active-count-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="avgReportApp.subActiveCount.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="address.id">
                    <Translate contentKey="avgReportApp.subActiveCount.address">Address</Translate>
                  </Label>
                  <AvInput id="sub-active-count-address" type="select" className="form-control" name="address.id">
                    <option value="" key="0" />
                    {addresses
                      ? addresses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="product.id">
                    <Translate contentKey="avgReportApp.subActiveCount.product">Product</Translate>
                  </Label>
                  <AvInput id="sub-active-count-product" type="select" className="form-control" name="product.id">
                    <option value="" key="0" />
                    {products
                      ? products.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="reporttime.id">
                    <Translate contentKey="avgReportApp.subActiveCount.reporttime">Reporttime</Translate>
                  </Label>
                  <AvInput id="sub-active-count-reporttime" type="select" className="form-control" name="reporttime.id">
                    <option value="" key="0" />
                    {reportTimes
                      ? reportTimes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="unit.id">
                    <Translate contentKey="avgReportApp.subActiveCount.unit">Unit</Translate>
                  </Label>
                  <AvInput id="sub-active-count-unit" type="select" className="form-control" name="unit.id">
                    <option value="" key="0" />
                    {ouunits
                      ? ouunits.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="promo.id">
                    <Translate contentKey="avgReportApp.subActiveCount.promo">Promo</Translate>
                  </Label>
                  <AvInput id="sub-active-count-promo" type="select" className="form-control" name="promo.id">
                    <option value="" key="0" />
                    {promoPrograms
                      ? promoPrograms.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sub-active-count" replace color="info">
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
  addresses: storeState.addresses.entities,
  products: storeState.products.entities,
  reportTimes: storeState.reportTime.entities,
  ouunits: storeState.ouunits.entities,
  promoPrograms: storeState.promoPrograms.entities,
  subActiveCountEntity: storeState.subActiveCount.entity,
  loading: storeState.subActiveCount.loading,
  updating: storeState.subActiveCount.updating,
  updateSuccess: storeState.subActiveCount.updateSuccess
});

const mapDispatchToProps = {
  getAddresses,
  getProducts,
  getReportTimes,
  getOuunits,
  getPromoPrograms,
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
)(SubActiveCountUpdate);
