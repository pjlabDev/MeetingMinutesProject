import { Usuario } from './usuario';
import { Reunion } from './reunion';
export class Tareas {
    codTarea: number;
    titulo: string;
    descripcion: string;
    usuarios: Usuario;
    cerrado: number;
    reunion: Reunion;
}
