import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sub-active-count.reducer';
import { ISubActiveCount } from 'app/shared/model/sub-active-count.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISubActiveCountDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SubActiveCountDetail extends React.Component<ISubActiveCountDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { subActiveCountEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="avgReportApp.subActiveCount.detail.title">SubActiveCount</Translate> [<b>{subActiveCountEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="subActiveCountID">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountID">Sub Active Count ID</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.subActiveCountID}</dd>
            <dt>
              <span id="subActiveCounts">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCounts">Sub Active Counts</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.subActiveCounts}</dd>
            <dt>
              <span id="subActiveCountUserField1">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField1">Sub Active Count User Field 1</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.subActiveCountUserField1}</dd>
            <dt>
              <span id="subActiveCountUserField2">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField2">Sub Active Count User Field 2</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.subActiveCountUserField2}</dd>
            <dt>
              <span id="subActiveCountUserField3">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField3">Sub Active Count User Field 3</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.subActiveCountUserField3}</dd>
            <dt>
              <span id="subActiveCountUserField4">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField4">Sub Active Count User Field 4</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.subActiveCountUserField4}</dd>
            <dt>
              <span id="subActiveCountUserField5">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountUserField5">Sub Active Count User Field 5</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.subActiveCountUserField5}</dd>
            <dt>
              <span id="subActiveCountDateCreated">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountDateCreated">Sub Active Count Date Created</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={subActiveCountEntity.subActiveCountDateCreated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="subActiveCountLastUpdated">
                <Translate contentKey="avgReportApp.subActiveCount.subActiveCountLastUpdated">Sub Active Count Last Updated</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={subActiveCountEntity.subActiveCountLastUpdated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="avgReportApp.subActiveCount.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{subActiveCountEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="avgReportApp.subActiveCount.address">Address</Translate>
            </dt>
            <dd>{subActiveCountEntity.address ? subActiveCountEntity.address.id : ''}</dd>
            <dt>
              <Translate contentKey="avgReportApp.subActiveCount.product">Product</Translate>
            </dt>
            <dd>{subActiveCountEntity.product ? subActiveCountEntity.product.id : ''}</dd>
            <dt>
              <Translate contentKey="avgReportApp.subActiveCount.reporttime">Reporttime</Translate>
            </dt>
            <dd>{subActiveCountEntity.reporttime ? subActiveCountEntity.reporttime.id : ''}</dd>
            <dt>
              <Translate contentKey="avgReportApp.subActiveCount.unit">Unit</Translate>
            </dt>
            <dd>{subActiveCountEntity.unit ? subActiveCountEntity.unit.id : ''}</dd>
            <dt>
              <Translate contentKey="avgReportApp.subActiveCount.promo">Promo</Translate>
            </dt>
            <dd>{subActiveCountEntity.promo ? subActiveCountEntity.promo.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/sub-active-count" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/sub-active-count/${subActiveCountEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ subActiveCount }: IRootState) => ({
  subActiveCountEntity: subActiveCount.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubActiveCountDetail);
