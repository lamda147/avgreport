import { Moment } from 'moment';

export interface IAddresses {
  id?: number;
  addressid?: string;
  addressarea?: string;
  addressdistrict?: string;
  addresscity?: string;
  addressstate?: string;
  addressuserfield1?: string;
  addressuserfield2?: string;
  addressuserfield3?: string;
  addressuserfield4?: string;
  addressuserfield5?: string;
  addressuserfield6?: string;
  addressDateCreated?: Moment;
  addressLastUpdated?: Moment;
  isDeleted?: boolean;
}

export const defaultValue: Readonly<IAddresses> = {
  isDeleted: false
};
