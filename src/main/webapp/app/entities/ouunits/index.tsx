import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ouunits from './ouunits';
import OuunitsDetail from './ouunits-detail';
import OuunitsUpdate from './ouunits-update';
import OuunitsDeleteDialog from './ouunits-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OuunitsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OuunitsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OuunitsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ouunits} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={OuunitsDeleteDialog} />
  </>
);

export default Routes;
