import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './products.reducer';
import { IProducts } from 'app/shared/model/products.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProductsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ProductsDetail extends React.Component<IProductsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { productsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="avgReportApp.products.detail.title">Products</Translate> [<b>{productsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="prodID">
                <Translate contentKey="avgReportApp.products.prodID">Prod ID</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodID}</dd>
            <dt>
              <span id="prodDesc">
                <Translate contentKey="avgReportApp.products.prodDesc">Prod Desc</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodDesc}</dd>
            <dt>
              <span id="prodCode">
                <Translate contentKey="avgReportApp.products.prodCode">Prod Code</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodCode}</dd>
            <dt>
              <span id="subTypeName">
                <Translate contentKey="avgReportApp.products.subTypeName">Sub Type Name</Translate>
              </span>
            </dt>
            <dd>{productsEntity.subTypeName}</dd>
            <dt>
              <span id="prodUserField1">
                <Translate contentKey="avgReportApp.products.prodUserField1">Prod User Field 1</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodUserField1}</dd>
            <dt>
              <span id="prodUserField2">
                <Translate contentKey="avgReportApp.products.prodUserField2">Prod User Field 2</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodUserField2}</dd>
            <dt>
              <span id="prodUserField3">
                <Translate contentKey="avgReportApp.products.prodUserField3">Prod User Field 3</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodUserField3}</dd>
            <dt>
              <span id="prodUserField4">
                <Translate contentKey="avgReportApp.products.prodUserField4">Prod User Field 4</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodUserField4}</dd>
            <dt>
              <span id="prodUserField5">
                <Translate contentKey="avgReportApp.products.prodUserField5">Prod User Field 5</Translate>
              </span>
            </dt>
            <dd>{productsEntity.prodUserField5}</dd>
            <dt>
              <span id="productDateCreated">
                <Translate contentKey="avgReportApp.products.productDateCreated">Product Date Created</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={productsEntity.productDateCreated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="productLastUpdated">
                <Translate contentKey="avgReportApp.products.productLastUpdated">Product Last Updated</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={productsEntity.productLastUpdated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="avgReportApp.products.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{productsEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/products" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/products/${productsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ products }: IRootState) => ({
  productsEntity: products.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProductsDetail);
