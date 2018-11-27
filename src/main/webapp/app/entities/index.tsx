import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Addresses from './addresses';
import Ouunits from './ouunits';
import Products from './products';
import PromoPrograms from './promo-programs';
import ReportTime from './report-time';
import SubActiveCount from './sub-active-count';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/addresses`} component={Addresses} />
      <ErrorBoundaryRoute path={`${match.url}/ouunits`} component={Ouunits} />
      <ErrorBoundaryRoute path={`${match.url}/products`} component={Products} />
      <ErrorBoundaryRoute path={`${match.url}/promo-programs`} component={PromoPrograms} />
      <ErrorBoundaryRoute path={`${match.url}/report-time`} component={ReportTime} />
      <ErrorBoundaryRoute path={`${match.url}/sub-active-count`} component={SubActiveCount} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
