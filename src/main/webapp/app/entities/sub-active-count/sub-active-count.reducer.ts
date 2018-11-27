import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISubActiveCount, defaultValue } from 'app/shared/model/sub-active-count.model';

export const ACTION_TYPES = {
  FETCH_SUBACTIVECOUNT_LIST: 'subActiveCount/FETCH_SUBACTIVECOUNT_LIST',
  FETCH_SUBACTIVECOUNT: 'subActiveCount/FETCH_SUBACTIVECOUNT',
  CREATE_SUBACTIVECOUNT: 'subActiveCount/CREATE_SUBACTIVECOUNT',
  UPDATE_SUBACTIVECOUNT: 'subActiveCount/UPDATE_SUBACTIVECOUNT',
  DELETE_SUBACTIVECOUNT: 'subActiveCount/DELETE_SUBACTIVECOUNT',
  RESET: 'subActiveCount/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISubActiveCount>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type SubActiveCountState = Readonly<typeof initialState>;

// Reducer

export default (state: SubActiveCountState = initialState, action): SubActiveCountState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SUBACTIVECOUNT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SUBACTIVECOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SUBACTIVECOUNT):
    case REQUEST(ACTION_TYPES.UPDATE_SUBACTIVECOUNT):
    case REQUEST(ACTION_TYPES.DELETE_SUBACTIVECOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SUBACTIVECOUNT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SUBACTIVECOUNT):
    case FAILURE(ACTION_TYPES.CREATE_SUBACTIVECOUNT):
    case FAILURE(ACTION_TYPES.UPDATE_SUBACTIVECOUNT):
    case FAILURE(ACTION_TYPES.DELETE_SUBACTIVECOUNT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBACTIVECOUNT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBACTIVECOUNT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SUBACTIVECOUNT):
    case SUCCESS(ACTION_TYPES.UPDATE_SUBACTIVECOUNT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SUBACTIVECOUNT):
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

const apiUrl = 'api/sub-active-counts';

// Actions

export const getEntities: ICrudGetAllAction<ISubActiveCount> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SUBACTIVECOUNT_LIST,
    payload: axios.get<ISubActiveCount>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ISubActiveCount> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SUBACTIVECOUNT,
    payload: axios.get<ISubActiveCount>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISubActiveCount> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SUBACTIVECOUNT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISubActiveCount> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SUBACTIVECOUNT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISubActiveCount> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SUBACTIVECOUNT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
