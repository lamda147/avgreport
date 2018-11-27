import { Moment } from 'moment';

export interface IOuunits {
  id?: number;
  ouunitid?: string;
  ouunitname?: string;
  parentouunit?: string;
  grandparentouunit?: string;
  ouunituserfield1?: string;
  ouunituserfield2?: string;
  ouunituserfield3?: string;
  ouunitDateCreated?: Moment;
  ouunitLastUpdated?: Moment;
  isDeleted?: boolean;
}

export const defaultValue: Readonly<IOuunits> = {
  isDeleted: false
};
