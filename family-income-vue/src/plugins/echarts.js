import Vue from 'vue'
import ECharts from 'vue-echarts'

import {
    CanvasRenderer
} from 'echarts/renderers'
import {
    BarChart
} from 'echarts/charts'
import {
    GridComponent,
    TooltipComponent
} from 'echarts/components'

Vue.use([
    CanvasRenderer,
    BarChart,
    GridComponent,
    TooltipComponent
]);

Vue.component('v-chart', ECharts)
