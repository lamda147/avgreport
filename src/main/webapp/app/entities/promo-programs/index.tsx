import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PromoPrograms from './promo-programs';
import PromoProgramsDetail from './promo-programs-detail';
import PromoProgramsUpdate from './promo-programs-update';
import PromoProgramsDeleteDialog from './promo-programs-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PromoProgramsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PromoProgramsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PromoProgramsDetail} />
      <ErrorBoundaryRoute path={match.url} component={PromoPrograms} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PromoProgramsDeleteDialog} />
  </>
);

export default Routes;
