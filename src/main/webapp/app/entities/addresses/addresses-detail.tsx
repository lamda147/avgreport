import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './addresses.reducer';
import { IAddresses } from 'app/shared/model/addresses.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddressesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AddressesDetail extends React.Component<IAddressesDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { addressesEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="avgReportApp.addresses.detail.title">Addresses</Translate> [<b>{addressesEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="addressid">
                <Translate contentKey="avgReportApp.addresses.addressid">Addressid</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressid}</dd>
            <dt>
              <span id="addressarea">
                <Translate contentKey="avgReportApp.addresses.addressarea">Addressarea</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressarea}</dd>
            <dt>
              <span id="addressdistrict">
                <Translate contentKey="avgReportApp.addresses.addressdistrict">Addressdistrict</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressdistrict}</dd>
            <dt>
              <span id="addresscity">
                <Translate contentKey="avgReportApp.addresses.addresscity">Addresscity</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addresscity}</dd>
            <dt>
              <span id="addressstate">
                <Translate contentKey="avgReportApp.addresses.addressstate">Addressstate</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressstate}</dd>
            <dt>
              <span id="addressuserfield1">
                <Translate contentKey="avgReportApp.addresses.addressuserfield1">Addressuserfield 1</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressuserfield1}</dd>
            <dt>
              <span id="addressuserfield2">
                <Translate contentKey="avgReportApp.addresses.addressuserfield2">Addressuserfield 2</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressuserfield2}</dd>
            <dt>
              <span id="addressuserfield3">
                <Translate contentKey="avgReportApp.addresses.addressuserfield3">Addressuserfield 3</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressuserfield3}</dd>
            <dt>
              <span id="addressuserfield4">
                <Translate contentKey="avgReportApp.addresses.addressuserfield4">Addressuserfield 4</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressuserfield4}</dd>
            <dt>
              <span id="addressuserfield5">
                <Translate contentKey="avgReportApp.addresses.addressuserfield5">Addressuserfield 5</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressuserfield5}</dd>
            <dt>
              <span id="addressuserfield6">
                <Translate contentKey="avgReportApp.addresses.addressuserfield6">Addressuserfield 6</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.addressuserfield6}</dd>
            <dt>
              <span id="addressDateCreated">
                <Translate contentKey="avgReportApp.addresses.addressDateCreated">Address Date Created</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={addressesEntity.addressDateCreated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="addressLastUpdated">
                <Translate contentKey="avgReportApp.addresses.addressLastUpdated">Address Last Updated</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={addressesEntity.addressLastUpdated} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="avgReportApp.addresses.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{addressesEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/addresses" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/addresses/${addressesEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ addresses }: IRootState) => ({
  addressesEntity: addresses.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AddressesDetail);
