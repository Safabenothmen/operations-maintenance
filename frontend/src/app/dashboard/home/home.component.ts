import { Component, OnInit } from '@angular/core';
import { HomeService } from './../../services/home.service';
import { ChartType } from 'chart.js'; // Importez le type ChartType depuis chart.js

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  stats: any;

  donutChartLabels = ['Pannes Résolues', 'Pannes en Attente'];
  donutChartData: number[] = [];
  donutChartType: ChartType = 'doughnut'; // Utilisez ChartType ici au lieu de string

  constructor(private HomeService: HomeService) {}

  ngOnInit(): void {
    this.HomeService.getStats().subscribe(data => {
      this.stats = data;
      this.donutChartData = [
        data.pannesResolues,
        data.pannesEnAttente
      ];
    });
  }
}
