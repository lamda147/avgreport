import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOuunits, defaultValue } from 'app/shared/model/ouunits.model';

export const ACTION_TYPES = {
  FETCH_OUUNITS_LIST: 'ouunits/FETCH_OUUNITS_LIST',
  FETCH_OUUNITS: 'ouunits/FETCH_OUUNITS',
  CREATE_OUUNITS: 'ouunits/CREATE_OUUNITS',
  UPDATE_OUUNITS: 'ouunits/UPDATE_OUUNITS',
  DELETE_OUUNITS: 'ouunits/DELETE_OUUNITS',
  RESET: 'ouunits/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOuunits>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type OuunitsState = Readonly<typeof initialState>;

// Reducer

export default (state: OuunitsState = initialState, action): OuunitsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_OUUNITS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_OUUNITS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_OUUNITS):
    case REQUEST(ACTION_TYPES.UPDATE_OUUNITS):
    case REQUEST(ACTION_TYPES.DELETE_OUUNITS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_OUUNITS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_OUUNITS):
    case FAILURE(ACTION_TYPES.CREATE_OUUNITS):
    case FAILURE(ACTION_TYPES.UPDATE_OUUNITS):
    case FAILURE(ACTION_TYPES.DELETE_OUUNITS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_OUUNITS_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_OUUNITS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_OUUNITS):
    case SUCCESS(ACTION_TYPES.UPDATE_OUUNITS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_OUUNITS):
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

const apiUrl = 'api/ouunits';

// Actions

export const getEntities: ICrudGetAllAction<IOuunits> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_OUUNITS_LIST,
    payload: axios.get<IOuunits>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IOuunits> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_OUUNITS,
    payload: axios.get<IOuunits>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IOuunits> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_OUUNITS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOuunits> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_OUUNITS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOuunits> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_OUUNITS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
