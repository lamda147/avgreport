import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './promo-programs.reducer';
import { IPromoPrograms } from 'app/shared/model/promo-programs.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPromoProgramsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PromoProgramsDetail extends React.Component<IPromoProgramsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { promoProgramsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="avgReportApp.promoPrograms.detail.title">PromoPrograms</Translate> [<b>{promoProgramsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="promoID">
                <Translate contentKey="avgReportApp.promoPrograms.promoID">Promo ID</Translate>
              </span>
            </dt>
            <dd>{promoProgramsEntity.promoID}</dd>
            <dt>
              <span id="promoname">
                <Translate contentKey="avgReportApp.promoPrograms.promoname">Promoname</Translate>
              </span>
            </dt>
            <dd>{promoProgramsEntity.promoname}</dd>
            <dt>
              <span id="promoUserField1">
                <Translate contentKey="avgReportApp.promoPrograms.promoUserField1">Promo User Field 1</Translate>
              </span>
            </dt>
            <dd>{promoProgramsEntity.promoUserField1}</dd>
            <dt>
              <span id="promoUserField2">
                <Translate contentKey="avgReportApp.promoPrograms.promoUserField2">Promo User Field 2</Translate>
              </span>
            </dt>
            <dd>{promoProgramsEntity.promoUserField2}</dd>
            <dt>
              <span id="promoUserField3">
                <Translate contentKey="avgReportApp.promoPrograms.promoUserField3">Promo User Field 3</Translate>
              </span>
            </dt>
            <dd>{promoProgramsEntity.promoUserField3}</dd>
            <dt>
              <span id="promoDateCreated">
                <Translate contentKey="avgReportApp.promoPrograms.promoDateCreated">Promo Date Created</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={promoProgramsEntity.promoDateCreated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="promoLastUpdated">
                <Translate contentKey="avgReportApp.promoPrograms.promoLastUpdated">Promo Last Updated</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={promoProgramsEntity.promoLastUpdated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="avgReportApp.promoPrograms.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{promoProgramsEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/promo-programs" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/promo-programs/${promoProgramsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ promoPrograms }: IRootState) => ({
  promoProgramsEntity: promoPrograms.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PromoProgramsDetail);
