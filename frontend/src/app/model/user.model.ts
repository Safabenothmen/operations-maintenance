export interface Technicien {
    id?: number;
    nom: string;
    email: string;
    mdp: string;
    role: 'Technicien';
    compétences: string[];
    disponibilité: boolean;
    interventions?: any[];
  }