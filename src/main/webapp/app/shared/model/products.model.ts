import { Moment } from 'moment';

export interface IProducts {
  id?: number;
  prodID?: string;
  prodDesc?: string;
  prodCode?: string;
  subTypeName?: string;
  prodUserField1?: string;
  prodUserField2?: string;
  prodUserField3?: string;
  prodUserField4?: string;
  prodUserField5?: string;
  productDateCreated?: Moment;
  productLastUpdated?: Moment;
  isDeleted?: boolean;
}

export const defaultValue: Readonly<IProducts> = {
  isDeleted: false
};
