import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SubActiveCount from './sub-active-count';
import SubActiveCountDetail from './sub-active-count-detail';
import SubActiveCountUpdate from './sub-active-count-update';
import SubActiveCountDeleteDialog from './sub-active-count-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SubActiveCountUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SubActiveCountUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SubActiveCountDetail} />
      <ErrorBoundaryRoute path={match.url} component={SubActiveCount} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SubActiveCountDeleteDialog} />
  </>
);

export default Routes;
