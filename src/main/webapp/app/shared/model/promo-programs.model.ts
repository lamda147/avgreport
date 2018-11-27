import { Moment } from 'moment';

export interface IPromoPrograms {
  id?: number;
  promoID?: string;
  promoname?: string;
  promoUserField1?: string;
  promoUserField2?: string;
  promoUserField3?: string;
  promoDateCreated?: Moment;
  promoLastUpdated?: Moment;
  isDeleted?: boolean;
}

export const defaultValue: Readonly<IPromoPrograms> = {
  isDeleted: false
};
