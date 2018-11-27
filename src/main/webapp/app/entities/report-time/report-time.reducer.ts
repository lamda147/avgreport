import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IReportTime, defaultValue } from 'app/shared/model/report-time.model';

export const ACTION_TYPES = {
  FETCH_REPORTTIME_LIST: 'reportTime/FETCH_REPORTTIME_LIST',
  FETCH_REPORTTIME: 'reportTime/FETCH_REPORTTIME',
  CREATE_REPORTTIME: 'reportTime/CREATE_REPORTTIME',
  UPDATE_REPORTTIME: 'reportTime/UPDATE_REPORTTIME',
  DELETE_REPORTTIME: 'reportTime/DELETE_REPORTTIME',
  RESET: 'reportTime/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IReportTime>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ReportTimeState = Readonly<typeof initialState>;

// Reducer

export default (state: ReportTimeState = initialState, action): ReportTimeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REPORTTIME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REPORTTIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_REPORTTIME):
    case REQUEST(ACTION_TYPES.UPDATE_REPORTTIME):
    case REQUEST(ACTION_TYPES.DELETE_REPORTTIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_REPORTTIME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REPORTTIME):
    case FAILURE(ACTION_TYPES.CREATE_REPORTTIME):
    case FAILURE(ACTION_TYPES.UPDATE_REPORTTIME):
    case FAILURE(ACTION_TYPES.DELETE_REPORTTIME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPORTTIME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPORTTIME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_REPORTTIME):
    case SUCCESS(ACTION_TYPES.UPDATE_REPORTTIME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_REPORTTIME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/report-times';

// Actions

export const getEntities: ICrudGetAllAction<IReportTime> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REPORTTIME_LIST,
  payload: axios.get<IReportTime>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IReportTime> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REPORTTIME,
    payload: axios.get<IReportTime>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IReportTime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REPORTTIME,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IReportTime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REPORTTIME,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IReportTime> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REPORTTIME,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
