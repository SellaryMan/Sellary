import Link from "next/link";
import React from "react";
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarHeader,
  SidebarGroupLabel,
  SidebarGroupContent,
  SidebarMenu,
  SidebarMenuItem
} from "@/components/ui/sidebar"
interface SidebarChild {
    title: string;
    path: string;
}
  
interface SidebarParent {
    parent: string;
    title : string;
    children: SidebarChild[];
}
type SidebarElementsType = SidebarParent[];

const SidebarElements : SidebarElementsType =[
    {
        parent : "outbound",
        title : "출고관리",
        children : [{
                title : "출고조회",
                path : "/outbound"
            },{
                title : "출고등록",
                path : "/outbound/register" 
            }
        ]
    },
    {
      parent : "products",
      title : "상품관리",
      children : [{
              title : "상품조회",
              path : "/"
          },{
              title : "상품등록",
              path : "/" 
          }
      ]
  }
]
const AppSidebar = () => {
    return (
        <Sidebar>
          <SidebarHeader />
          <SidebarContent>
            
              {SidebarElements.map((el: SidebarParent, idx: number) => (
              <div key={idx} className="flex flex-col gap-1">
                <SidebarGroup>
                  <SidebarGroupLabel className="text-gray-700">{el.title}</SidebarGroupLabel>
                    <SidebarGroupContent>
                      <SidebarMenu>
                      <div className="flex flex-col gap-1">
                      {el.children.map((child, childIndex) => (
                        <SidebarMenuItem
                          key={childIndex} >
                            <Link 
                            href={child.path}
                            className="text-xs ml-3 text-gray-500 hover:text-gray-800"
                        >
                          {child.title}
                        </Link>
                        </SidebarMenuItem>
                      ))}
                    </div>
                      </SidebarMenu>
                    </SidebarGroupContent>
                  </SidebarGroup>
                </div>
            ))}

          </SidebarContent>
          <SidebarFooter />
        </Sidebar>
        // <aside className="flex flex-col gap-2 p-4 border-gray-200 border-r-[0.2px] shadow-xs shadow-right bg-gray-200 min-w-36 z-999">
        // {SidebarElements.map((el: SidebarParent, idx: number) => (
        //     <div key={idx} className="flex flex-col gap-1">
        //       <div className="font-medium text-gray-700">{el.name}</div>
              
        //       <div className="flex flex-col gap-1">
        //         {el.children.map((child, childIndex) => (
        //           <Link 
        //             key={childIndex} 
        //             href={child.path}
        //             className="text-s text-gray-500 hover:text-gray-800"
        //           >
        //             {child.name}
        //           </Link>
        //         ))}
        //       </div>
        //     </div>
        //   ))}
        // </aside>
    );
  };
  
  export default AppSidebar;