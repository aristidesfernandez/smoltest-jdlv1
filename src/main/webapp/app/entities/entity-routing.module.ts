import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'event-type',
        data: { pageTitle: 'smolPlusTempApp.eventType.home.title' },
        loadChildren: () => import('./event-type/event-type.module').then(m => m.EventTypeModule),
      },
      {
        path: 'establishment',
        data: { pageTitle: 'smolPlusTempApp.establishment.home.title' },
        loadChildren: () => import('./establishment/establishment.module').then(m => m.EstablishmentModule),
      },
      {
        path: 'event-device',
        data: { pageTitle: 'smolPlusTempApp.eventDevice.home.title' },
        loadChildren: () => import('./event-device/event-device.module').then(m => m.EventDeviceModule),
      },
      {
        path: 'device-establishment',
        data: { pageTitle: 'smolPlusTempApp.deviceEstablishment.home.title' },
        loadChildren: () => import('./device-establishment/device-establishment.module').then(m => m.DeviceEstablishmentModule),
      },
      {
        path: 'counter-type',
        data: { pageTitle: 'smolPlusTempApp.counterType.home.title' },
        loadChildren: () => import('./counter-type/counter-type.module').then(m => m.CounterTypeModule),
      },
      {
        path: 'counter-event',
        data: { pageTitle: 'smolPlusTempApp.counterEvent.home.title' },
        loadChildren: () => import('./counter-event/counter-event.module').then(m => m.CounterEventModule),
      },
      {
        path: 'device-type',
        data: { pageTitle: 'smolPlusTempApp.deviceType.home.title' },
        loadChildren: () => import('./device-type/device-type.module').then(m => m.DeviceTypeModule),
      },
      {
        path: 'device-category',
        data: { pageTitle: 'smolPlusTempApp.deviceCategory.home.title' },
        loadChildren: () => import('./device-category/device-category.module').then(m => m.DeviceCategoryModule),
      },
      {
        path: 'island',
        data: { pageTitle: 'smolPlusTempApp.island.home.title' },
        loadChildren: () => import('./island/island.module').then(m => m.IslandModule),
      },
      {
        path: 'currency-type',
        data: { pageTitle: 'smolPlusTempApp.currencyType.home.title' },
        loadChildren: () => import('./currency-type/currency-type.module').then(m => m.CurrencyTypeModule),
      },
      {
        path: 'device',
        data: { pageTitle: 'smolPlusTempApp.device.home.title' },
        loadChildren: () => import('./device/device.module').then(m => m.DeviceModule),
      },
      {
        path: 'counter-device',
        data: { pageTitle: 'smolPlusTempApp.counterDevice.home.title' },
        loadChildren: () => import('./counter-device/counter-device.module').then(m => m.CounterDeviceModule),
      },
      {
        path: 'manufacturer',
        data: { pageTitle: 'smolPlusTempApp.manufacturer.home.title' },
        loadChildren: () => import('./manufacturer/manufacturer.module').then(m => m.ManufacturerModule),
      },
      {
        path: 'formula',
        data: { pageTitle: 'smolPlusTempApp.formula.home.title' },
        loadChildren: () => import('./formula/formula.module').then(m => m.FormulaModule),
      },
      {
        path: 'model',
        data: { pageTitle: 'smolPlusTempApp.model.home.title' },
        loadChildren: () => import('./model/model.module').then(m => m.ModelModule),
      },
      {
        path: 'interfacing',
        data: { pageTitle: 'smolPlusTempApp.interfacing.home.title' },
        loadChildren: () => import('./interfacing/interfacing.module').then(m => m.InterfacingModule),
      },
      {
        path: 'operator',
        data: { pageTitle: 'smolPlusTempApp.operator.home.title' },
        loadChildren: () => import('./operator/operator.module').then(m => m.OperatorModule),
      },
      {
        path: 'operational-properties-establishment',
        data: { pageTitle: 'smolPlusTempApp.operationalPropertiesEstablishment.home.title' },
        loadChildren: () =>
          import('./operational-properties-establishment/operational-properties-establishment.module').then(
            m => m.OperationalPropertiesEstablishmentModule
          ),
      },
      {
        path: 'user-access',
        data: { pageTitle: 'smolPlusTempApp.userAccess.home.title' },
        loadChildren: () => import('./user-access/user-access.module').then(m => m.UserAccessModule),
      },
      {
        path: 'municipality',
        data: { pageTitle: 'smolPlusTempApp.municipality.home.title' },
        loadChildren: () => import('./municipality/municipality.module').then(m => m.MunicipalityModule),
      },
      {
        path: 'department',
        data: { pageTitle: 'smolPlusTempApp.department.home.title' },
        loadChildren: () => import('./department/department.module').then(m => m.DepartmentModule),
      },
      {
        path: 'country',
        data: { pageTitle: 'smolPlusTempApp.country.home.title' },
        loadChildren: () => import('./country/country.module').then(m => m.CountryModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
