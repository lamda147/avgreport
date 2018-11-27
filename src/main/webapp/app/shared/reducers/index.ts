import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import addresses, {
  AddressesState
} from 'app/entities/addresses/addresses.reducer';
// prettier-ignore
import ouunits, {
  OuunitsState
} from 'app/entities/ouunits/ouunits.reducer';
// prettier-ignore
import products, {
  ProductsState
} from 'app/entities/products/products.reducer';
// prettier-ignore
import promoPrograms, {
  PromoProgramsState
} from 'app/entities/promo-programs/promo-programs.reducer';
// prettier-ignore
import reportTime, {
  ReportTimeState
} from 'app/entities/report-time/report-time.reducer';
// prettier-ignore
import subActiveCount, {
  SubActiveCountState
} from 'app/entities/sub-active-count/sub-active-count.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly addresses: AddressesState;
  readonly ouunits: OuunitsState;
  readonly products: ProductsState;
  readonly promoPrograms: PromoProgramsState;
  readonly reportTime: ReportTimeState;
  readonly subActiveCount: SubActiveCountState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  addresses,
  ouunits,
  products,
  promoPrograms,
  reportTime,
  subActiveCount,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
