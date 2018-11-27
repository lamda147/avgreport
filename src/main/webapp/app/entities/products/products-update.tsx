import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './products.reducer';
import { IProducts } from 'app/shared/model/products.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProductsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IProductsUpdateState {
  isNew: boolean;
}

export class ProductsUpdate extends React.Component<IProductsUpdateProps, IProductsUpdateState> {
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
    values.productDateCreated = new Date(values.productDateCreated);
    values.productLastUpdated = new Date(values.productLastUpdated);

    if (errors.length === 0) {
      const { productsEntity } = this.props;
      const entity = {
        ...productsEntity,
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
    this.props.history.push('/entity/products');
  };

  render() {
    const { productsEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="avgReportApp.products.home.createOrEditLabel">
              <Translate contentKey="avgReportApp.products.home.createOrEditLabel">Create or edit a Products</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : productsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="products-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="prodIDLabel" for="prodID">
                    <Translate contentKey="avgReportApp.products.prodID">Prod ID</Translate>
                  </Label>
                  <AvField id="products-prodID" type="text" name="prodID" />
                </AvGroup>
                <AvGroup>
                  <Label id="prodDescLabel" for="prodDesc">
                    <Translate contentKey="avgReportApp.products.prodDesc">Prod Desc</Translate>
                  </Label>
                  <AvField id="products-prodDesc" type="text" name="prodDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="prodCodeLabel" for="prodCode">
                    <Translate contentKey="avgReportApp.products.prodCode">Prod Code</Translate>
                  </Label>
                  <AvField id="products-prodCode" type="text" name="prodCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="subTypeNameLabel" for="subTypeName">
                    <Translate contentKey="avgReportApp.products.subTypeName">Sub Type Name</Translate>
                  </Label>
                  <AvField id="products-subTypeName" type="text" name="subTypeName" />
                </AvGroup>
                <AvGroup>
                  <Label id="prodUserField1Label" for="prodUserField1">
                    <Translate contentKey="avgReportApp.products.prodUserField1">Prod User Field 1</Translate>
                  </Label>
                  <AvField id="products-prodUserField1" type="text" name="prodUserField1" />
                </AvGroup>
                <AvGroup>
                  <Label id="prodUserField2Label" for="prodUserField2">
                    <Translate contentKey="avgReportApp.products.prodUserField2">Prod User Field 2</Translate>
                  </Label>
                  <AvField id="products-prodUserField2" type="text" name="prodUserField2" />
                </AvGroup>
                <AvGroup>
                  <Label id="prodUserField3Label" for="prodUserField3">
                    <Translate contentKey="avgReportApp.products.prodUserField3">Prod User Field 3</Translate>
                  </Label>
                  <AvField id="products-prodUserField3" type="text" name="prodUserField3" />
                </AvGroup>
                <AvGroup>
                  <Label id="prodUserField4Label" for="prodUserField4">
                    <Translate contentKey="avgReportApp.products.prodUserField4">Prod User Field 4</Translate>
                  </Label>
                  <AvField id="products-prodUserField4" type="text" name="prodUserField4" />
                </AvGroup>
                <AvGroup>
                  <Label id="prodUserField5Label" for="prodUserField5">
                    <Translate contentKey="avgReportApp.products.prodUserField5">Prod User Field 5</Translate>
                  </Label>
                  <AvField id="products-prodUserField5" type="text" name="prodUserField5" />
                </AvGroup>
                <AvGroup>
                  <Label id="productDateCreatedLabel" for="productDateCreated">
                    <Translate contentKey="avgReportApp.products.productDateCreated">Product Date Created</Translate>
                  </Label>
                  <AvInput
                    id="products-productDateCreated"
                    type="datetime-local"
                    className="form-control"
                    name="productDateCreated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.productsEntity.productDateCreated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="productLastUpdatedLabel" for="productLastUpdated">
                    <Translate contentKey="avgReportApp.products.productLastUpdated">Product Last Updated</Translate>
                  </Label>
                  <AvInput
                    id="products-productLastUpdated"
                    type="datetime-local"
                    className="form-control"
                    name="productLastUpdated"
                    value={isNew ? null : convertDateTimeFromServer(this.props.productsEntity.productLastUpdated)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="products-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="avgReportApp.products.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/products" replace color="info">
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
  productsEntity: storeState.products.entity,
  loading: storeState.products.loading,
  updating: storeState.products.updating,
  updateSuccess: storeState.products.updateSuccess
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
)(ProductsUpdate);
