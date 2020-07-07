import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AddCampaignPage } from './add-campaign';

@NgModule({
  declarations: [
    AddCampaignPage,
  ],
  imports: [
    IonicPageModule.forChild(AddCampaignPage),
  ],
  exports : [
    AddCampaignPage
  ]
})
export class AddCampaignPageModule {}
