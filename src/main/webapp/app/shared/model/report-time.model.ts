export interface IReportTime {
  id?: number;
  reportTimeID?: string;
  reportTimeHour?: number;
  reportTimeDay?: number;
  reportTimeYearWeek?: number;
  reportTimeMonth?: number;
  reportTimeYearQuater?: number;
  reportTimeYear?: number;
  reportTimeUserField1?: string;
  reportTimeUserField2?: string;
  reportTimeUserField3?: string;
  isDeleted?: boolean;
}

export const defaultValue: Readonly<IReportTime> = {
  isDeleted: false
};
