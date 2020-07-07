import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { CampaignsPage } from './campaigns';

@NgModule({
  declarations: [
    CampaignsPage,
  ],
  imports: [
    IonicPageModule.forChild(CampaignsPage),
  ],
  exports : [
    CampaignsPage
  ]
})
export class CampaignsPageModule {}
