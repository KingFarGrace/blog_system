<template>
  <div>
    <div class="friend-head">


      <el-dropdown>
        <el-button class="friend-add" plain size="mini" >
          <i class="el-icon-plus"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="AddFriend = true">添加好友</el-dropdown-item>
          <el-dropdown-item @click.native="AddGroup = true">创建分组</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

      <el-dialog width="28%" title="添加好友" :visible.sync="AddFriend" @close='closeDialog'>
        <el-form :model="Addfriendform" :rules="rules" >                   <!-- 需要设置ref属性吗-->
          <el-form-item label="好友用户名" :label-width="formLabelWidth"  prop="name">
            <el-input v-model="Addfriendform.name" ></el-input>
          </el-form-item>
          <el-form-item label="分组" :label-width="formLabelWidth" prop="value">
            <el-select v-model="Addfriendform.value" placeholder="请选择">
              <el-option
                v-for="item in group"
                :key="item.value"
                :label="item.name"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="AddFriend = false">取 消</el-button>
          <el-button type="primary" @click="addsubmit(Addfriendform)">确 定</el-button>
        </div>
      </el-dialog>


      <el-dialog width="28%" title="新添分组" :visible.sync="AddGroup" @close='closeDialog'>
        <el-form :model="Addgroupform" :rules="rules" >
          <el-form-item label="组名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="Addgroupform.name" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="AddGroup = false">取 消</el-button>
          <el-button type="primary" @click="groupsubmit(Addgroupform)">确 定</el-button>
        </div>
      </el-dialog>

    </div>


    <div>
      <el-collapse v-model="activeName" accordion>
        <el-collapse-item
          v-for="(item, index) in group"
          :title="item.name"
          :name="index "
          :key="item.key"
        >
          <div class="top1">
            <el-scrollbar>
              <div class="child1">
                <div v-for="i in friend" :key="i"  v-if="item.value==i.value">         <!-- 两个value相同说明是该组下的  -->
                  {{i.name}}
                </div>
              </div>
            </el-scrollbar>
          </div>
        </el-collapse-item>
      </el-collapse>

    </div>

  </div>

</template>

<script>
  export default {
    data() {
      return {
        activeName: '',


        AddFriend: false,
        AddGroup:false,
        formLabelWidth: '100px',

        friend: [
          {
            name: '蒹葭',
            value:'1'
          },
          {
            name: '白鹭',
            value:'2'
          },
          {
            name: '伊人',
            value:'3'
          },
          {
            name: '水方',
            value:'1'
          },
        ],

        group:[
          {
            name:'我的好友',
            value:'1'
          },
          {
            name:'家人',
            value:'2'
          },
          {
            name:'朋友',
            value:'3'
          },
          {
            name:'黑名单',
            value:'4'
          },
        ],

        Addfriendform: {
          name: '',
          value:''
        },

        Addgroupform:{
          name: '',
          value:'',  //加入时候怎么添加value区别其他组呢
        },


        rules: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          value:[
            {required: true, message: '请选择', trigger: 'blur'}
          ]
        },

      };
    },
    methods: {
      closeDialog(){
        this.Addfriendform.name='';
        this.Addfriendform.value='';  /*组号，应该在数据库为自增*/
        this.Addgroupform.name='';
      },

      addsubmit(Addform){
        // alert('略略略略略')   //TODO 将新朋友存入数据库，并传给 friend数组
      },
      // Submit(Addfriendform) {
      //   this.$refs['form'].validate(valid => {
      //     if(valid) { // 请求后台接口  表示验证通过
      //       // 请求后台接口  进行操作
      //       // 请求成功之后 清除输入框汇中的内容
      //       this.$nextTick(() => {
      //         // form对应你写的<el-form ref="form"></el-form>
      //         this.$refs["form"].resetFields();
      //       });
      //     }
      //   })
      // },

      groupsubmit(Addgroupform){
        alert('添加组')     //TODO 将组名传入数据库，将组名赋值给   group.name

      },
    }
  }
</script>




<style>
  .top1 {
    height: 280px;
    background-color: #ffffff;
  }
  .top1 .child1 {
    height: 280px;
    /*background-color: rgb(214, 221, 185);*/
  }
  .friend-head{
    height: 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-right: 0;
    background-color: #f9f9f9;
  }
  .friend-add{
    background-color:transparent;
    border-style:none;
  }
</style>
