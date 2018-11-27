import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ouunits.reducer';
import { IOuunits } from 'app/shared/model/ouunits.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOuunitsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class OuunitsDetail extends React.Component<IOuunitsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { ouunitsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="avgReportApp.ouunits.detail.title">Ouunits</Translate> [<b>{ouunitsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="ouunitid">
                <Translate contentKey="avgReportApp.ouunits.ouunitid">Ouunitid</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.ouunitid}</dd>
            <dt>
              <span id="ouunitname">
                <Translate contentKey="avgReportApp.ouunits.ouunitname">Ouunitname</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.ouunitname}</dd>
            <dt>
              <span id="parentouunit">
                <Translate contentKey="avgReportApp.ouunits.parentouunit">Parentouunit</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.parentouunit}</dd>
            <dt>
              <span id="grandparentouunit">
                <Translate contentKey="avgReportApp.ouunits.grandparentouunit">Grandparentouunit</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.grandparentouunit}</dd>
            <dt>
              <span id="ouunituserfield1">
                <Translate contentKey="avgReportApp.ouunits.ouunituserfield1">Ouunituserfield 1</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.ouunituserfield1}</dd>
            <dt>
              <span id="ouunituserfield2">
                <Translate contentKey="avgReportApp.ouunits.ouunituserfield2">Ouunituserfield 2</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.ouunituserfield2}</dd>
            <dt>
              <span id="ouunituserfield3">
                <Translate contentKey="avgReportApp.ouunits.ouunituserfield3">Ouunituserfield 3</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.ouunituserfield3}</dd>
            <dt>
              <span id="ouunitDateCreated">
                <Translate contentKey="avgReportApp.ouunits.ouunitDateCreated">Ouunit Date Created</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={ouunitsEntity.ouunitDateCreated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ouunitLastUpdated">
                <Translate contentKey="avgReportApp.ouunits.ouunitLastUpdated">Ouunit Last Updated</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={ouunitsEntity.ouunitLastUpdated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="avgReportApp.ouunits.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{ouunitsEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/ouunits" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ouunits/${ouunitsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ ouunits }: IRootState) => ({
  ouunitsEntity: ouunits.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OuunitsDetail);
