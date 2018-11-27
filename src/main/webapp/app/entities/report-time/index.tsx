import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ReportTime from './report-time';
import ReportTimeDetail from './report-time-detail';
import ReportTimeUpdate from './report-time-update';
import ReportTimeDeleteDialog from './report-time-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ReportTimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ReportTimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ReportTimeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ReportTime} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ReportTimeDeleteDialog} />
  </>
);

export default Routes;
