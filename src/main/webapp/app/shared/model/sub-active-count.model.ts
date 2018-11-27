import { Moment } from 'moment';
import { IAddresses } from 'app/shared/model//addresses.model';
import { IProducts } from 'app/shared/model//products.model';
import { IReportTime } from 'app/shared/model//report-time.model';
import { IOuunits } from 'app/shared/model//ouunits.model';
import { IPromoPrograms } from 'app/shared/model//promo-programs.model';

export interface ISubActiveCount {
  id?: number;
  subActiveCountID?: string;
  subActiveCounts?: number;
  subActiveCountUserField1?: string;
  subActiveCountUserField2?: string;
  subActiveCountUserField3?: string;
  subActiveCountUserField4?: string;
  subActiveCountUserField5?: string;
  subActiveCountDateCreated?: Moment;
  subActiveCountLastUpdated?: Moment;
  isDeleted?: boolean;
  address?: IAddresses;
  product?: IProducts;
  reporttime?: IReportTime;
  unit?: IOuunits;
  promo?: IPromoPrograms;
}

export const defaultValue: Readonly<ISubActiveCount> = {
  isDeleted: false
};
