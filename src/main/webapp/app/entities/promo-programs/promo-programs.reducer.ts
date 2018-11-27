import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPromoPrograms, defaultValue } from 'app/shared/model/promo-programs.model';

export const ACTION_TYPES = {
  FETCH_PROMOPROGRAMS_LIST: 'promoPrograms/FETCH_PROMOPROGRAMS_LIST',
  FETCH_PROMOPROGRAMS: 'promoPrograms/FETCH_PROMOPROGRAMS',
  CREATE_PROMOPROGRAMS: 'promoPrograms/CREATE_PROMOPROGRAMS',
  UPDATE_PROMOPROGRAMS: 'promoPrograms/UPDATE_PROMOPROGRAMS',
  DELETE_PROMOPROGRAMS: 'promoPrograms/DELETE_PROMOPROGRAMS',
  RESET: 'promoPrograms/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPromoPrograms>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PromoProgramsState = Readonly<typeof initialState>;

// Reducer

export default (state: PromoProgramsState = initialState, action): PromoProgramsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROMOPROGRAMS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROMOPROGRAMS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROMOPROGRAMS):
    case REQUEST(ACTION_TYPES.UPDATE_PROMOPROGRAMS):
    case REQUEST(ACTION_TYPES.DELETE_PROMOPROGRAMS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROMOPROGRAMS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROMOPROGRAMS):
    case FAILURE(ACTION_TYPES.CREATE_PROMOPROGRAMS):
    case FAILURE(ACTION_TYPES.UPDATE_PROMOPROGRAMS):
    case FAILURE(ACTION_TYPES.DELETE_PROMOPROGRAMS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROMOPROGRAMS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROMOPROGRAMS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROMOPROGRAMS):
    case SUCCESS(ACTION_TYPES.UPDATE_PROMOPROGRAMS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROMOPROGRAMS):
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

const apiUrl = 'api/promo-programs';

// Actions

export const getEntities: ICrudGetAllAction<IPromoPrograms> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROMOPROGRAMS_LIST,
  payload: axios.get<IPromoPrograms>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPromoPrograms> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROMOPROGRAMS,
    payload: axios.get<IPromoPrograms>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPromoPrograms> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROMOPROGRAMS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPromoPrograms> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROMOPROGRAMS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPromoPrograms> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROMOPROGRAMS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
